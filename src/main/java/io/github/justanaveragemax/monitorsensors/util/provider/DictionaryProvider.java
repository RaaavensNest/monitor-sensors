package io.github.justanaveragemax.monitorsensors.util.provider;

import java.util.List;

public interface DictionaryProvider {

  String getDictionaryName();
  List<String> getAvailableValues();

}
