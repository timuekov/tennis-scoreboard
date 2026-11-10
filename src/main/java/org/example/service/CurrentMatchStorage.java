package org.example.service;

import org.example.model.Match;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CurrentMatchStorage {
    private final Map<UUID, Match> matchStorage = new ConcurrentHashMap<>();

    public UUID addMatch(Match match) {
        UUID id = UUID.randomUUID();
        matchStorage.put(id, match);
        return id;
    }

    public Match getMatch(UUID id) {
        return matchStorage.get(id);
    }

    public void removeMatch(UUID id) {
        matchStorage.remove(id);
    }

}
