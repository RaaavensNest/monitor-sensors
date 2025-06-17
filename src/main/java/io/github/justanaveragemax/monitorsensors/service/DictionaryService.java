package io.github.justanaveragemax.monitorsensors.service;

import java.util.List;

public interface DictionaryService {

  boolean isValueValid(String dictionary, String value);
  List<String> getAvailableValues(String dictionary);
}
