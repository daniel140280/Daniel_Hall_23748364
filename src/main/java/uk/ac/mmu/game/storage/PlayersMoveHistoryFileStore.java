package uk.ac.mmu.game.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
public class PlayersMoveHistoryFileStore {

    private final Path baseDir;
    private final ObjectMapper objectMapper;

    public PlayersMoveHistoryFileStore(
            @Value("${storage.move-history-dir}") String dir,
            ObjectMapper objectMapper
    ) throws IOException {
        this.baseDir = Paths.get(dir).toAbsolutePath().normalize();
        Files.createDirectories(this.baseDir);

        this.objectMapper = objectMapper.copy().enable(SerializationFeature.INDENT_OUTPUT);
    }

    public void save(String id, List<String> history) throws IOException {
        Path file = resolveSafeFile(id);
        Path tmp = Files.createTempFile(baseDir, "move-history-", ".tmp");

        try {
            objectMapper.writeValue(tmp.toFile(), history);
            Files.move(tmp, file, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
        } finally {
            Files.deleteIfExists(tmp);
        }
    }

    public Optional<List<String>> load(String id) throws IOException {
        Path file = resolveSafeFile(id);
        if (!Files.exists(file)) return Optional.empty();
        List<String> history = objectMapper.readValue(file.toFile(), List.class);
        return Optional.of(history);
    }

    private Path resolveSafeFile(String id) {
        String safe = id == null ? "" : id.replaceAll("[^a-zA-Z0-9_-]", "");
        if (safe.isBlank()) {
            throw new IllegalArgumentException("Invalid id (must contain letters/numbers/_/-).");
        }

        Path file = baseDir.resolve(safe + ".json").normalize();
        if (!file.startsWith(baseDir)) {
            throw new IllegalArgumentException("Invalid id.");
        }
        return file;
    }
}
