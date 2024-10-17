package com.searchy.app.services.search.models;

import java.util.List;

public record SearchEngineResult(String searchEngineName, List<SingleQueryResult> queryResults) {
}
