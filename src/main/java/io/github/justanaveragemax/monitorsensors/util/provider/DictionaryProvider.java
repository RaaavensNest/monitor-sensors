package io.github.justanaveragemax.monitorsensors.util.provider;

import io.github.justanaveragemax.monitorsensors.util.DictionaryType;
import java.util.List;

public interface DictionaryProvider {

  DictionaryType getDictionaryType();
  List<String> getAvailableValues();

}
