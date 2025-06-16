package io.github.justanaveragemax.monitorsensors.service.impl;

import io.github.justanaveragemax.monitorsensors.service.DictionaryService;
import io.github.justanaveragemax.monitorsensors.util.provider.DictionaryProvider;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class DictionaryServiceImpl implements DictionaryService {

  private final Map<String, DictionaryProvider> providers;

  public DictionaryServiceImpl(List<DictionaryProvider> providers){
    this.providers = providers.stream()
        .collect(Collectors.toMap(DictionaryProvider::getDictionaryName, p -> p));
  }

  @Override
  public boolean isValueValid(String dictionary, String value) {
    DictionaryProvider provider = providers.get(dictionary);
    return provider.getAvailableValues().contains(value.toUpperCase());
  }

  @Override
  public List<String> getAvailableValues(String dictionary) {
    return providers.get(dictionary).getAvailableValues();
  }
}
