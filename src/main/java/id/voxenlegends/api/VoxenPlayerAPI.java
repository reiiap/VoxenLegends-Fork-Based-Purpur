package id.voxenlegends.api;
import java.util.Optional;
import java.util.UUID;
public interface VoxenPlayerAPI { Optional<String> characterName(UUID playerId); }
