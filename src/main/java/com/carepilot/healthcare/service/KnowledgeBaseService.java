package com.carepilot.healthcare.service;

import com.carepilot.healthcare.model.Citation;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class KnowledgeBaseService {
    public record Chunk(String source, String section, String text) {}
    private final List<Chunk> chunks = new CopyOnWriteArrayList<>();
    private final Path uploadDir = Path.of("uploads");

    public KnowledgeBaseService() {
        loadBuiltInKnowledge();
    }

    private void loadBuiltInKnowledge() {
        try {
            Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:knowledge-base/*");
            for (Resource resource : resources) {
                if (resource.isReadable()) {
                    String text = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
                    addText(resource.getFilename(), "Approved policy", text);
                }
            }
        } catch (IOException ignored) { }
    }

    private void addText(String source, String section, String text) {
        String[] parts = text.split("(?=\\n#{1,3} )|(?<=\\.)\\s+(?=[A-Z])");
        for (String part : parts) {
            if (part.isBlank()) continue;
            chunks.add(new Chunk(source, section, part.trim()));
        }
    }

    public Map<String,Object> upload(MultipartFile file) throws IOException {
        Files.createDirectories(uploadDir);
        Path target = uploadDir.resolve(Path.of(Objects.requireNonNull(file.getOriginalFilename())).getFileName().toString());
        Files.write(target, file.getBytes());
        return Map.of("filename", target.getFileName().toString(), "size", file.getSize());
    }

    public int ingest() { return chunks.size(); }

    public List<Map.Entry<Chunk, Double>> search(String query, int limit) {
        Set<String> terms = new HashSet<>(Arrays.asList(query.toLowerCase(Locale.ROOT).split("\\W+")));
        return chunks.stream().map(c -> {
            String text = c.text().toLowerCase(Locale.ROOT);
            long hits = terms.stream().filter(t -> t.length() > 2 && text.contains(t)).count();
            double score = terms.isEmpty() ? 0 : Math.min(0.99, (double) hits / terms.stream().filter(t -> t.length() > 2).count());
            return Map.entry(c, score);
        }).filter(e -> e.getValue() > 0).sorted(Map.Entry.<Chunk,Double>comparingByValue().reversed()).limit(limit).toList();
    }

    public List<Citation> citations(String query) {
        return search(query, 5).stream().map(e -> new Citation(e.getKey().source(), e.getKey().section(), e.getValue())).toList();
    }

    public int size() { return chunks.size(); }
    public int dimensions() { return 1536; }
}
