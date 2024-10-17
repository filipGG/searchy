package com.searchy.app.services.search.models;

public record SingleQueryResult(String query, long hits, String error) {
}
