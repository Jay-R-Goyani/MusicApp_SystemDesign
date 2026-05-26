package Adapter;

import java.util.EnumMap;
import java.util.Map;

public class ProviderRegistry {
    private final Map<MusicProvider, MusicProviderAdapter> adapters = new EnumMap<>(MusicProvider.class);
    private static ProviderRegistry instance;

    private ProviderRegistry() {
    }

    public static ProviderRegistry getInstance() {
        if (instance == null) {
            instance = new ProviderRegistry();
        }
        return instance;
    }

    public void register(MusicProviderAdapter adapter) {
        adapters.put(adapter.getType(), adapter);
    }

    public MusicProviderAdapter get(MusicProvider type) {
        MusicProviderAdapter adapter = adapters.get(type);
        if (adapter == null) {
            throw new IllegalArgumentException("Provider not registered: " + type);
        }
        return adapter;
    }
}