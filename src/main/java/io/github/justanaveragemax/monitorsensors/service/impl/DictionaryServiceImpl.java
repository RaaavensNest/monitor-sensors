package io.github.justanaveragemax.monitorsensors.service.impl;

import io.github.justanaveragemax.monitorsensors.service.DictionaryService;
import io.github.justanaveragemax.monitorsensors.util.DictionaryType;
import io.github.justanaveragemax.monitorsensors.util.provider.DictionaryProvider;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class DictionaryServiceImpl implements DictionaryService {

  private final Map<DictionaryType, DictionaryProvider> providers;

  public DictionaryServiceImpl(List<DictionaryProvider> providers) {
    this.providers = providers.stream()
        .collect(Collectors.toMap(
            DictionaryProvider::getDictionaryType, Function.identity()
        ));
  }

  @Override
  public boolean isValueValid(String dictionary, String value) {
    DictionaryProvider provider = providers.get(DictionaryType.fromValue(dictionary));
    if (provider == null || value == null) {
      return false;
    }
    return provider.getAvailableValues().contains(value.toUpperCase());
  }

  @Override
  public List<String> getAvailableValues(String dictionary) {
    return providers.get(DictionaryType.fromValue(dictionary)).getAvailableValues();
  }
}
