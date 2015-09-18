/*
 * Copyright 2000-2010 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.application.options.codeStyle;

import com.intellij.openapi.application.ApplicationBundle;
import com.intellij.psi.codeStyle.CodeStyleSettingRepresentation;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;

import java.util.Map;
import java.util.Set;

public class CodeStyleSpacesPanel extends OptionTreeWithPreviewPanel {
  public CodeStyleSpacesPanel(CodeStyleSettings settings) {
    super(settings);
    init();
  }

  @Override
  public LanguageCodeStyleSettingsProvider.SettingsType getSettingsType() {
    return LanguageCodeStyleSettingsProvider.SettingsType.SPACING_SETTINGS;
  }

  @Override
  protected void initTables() {
    Map<CodeStyleSettingRepresentation.SettingsGroup, Set<CodeStyleSettingRepresentation>> settingsMap = CodeStyleSettingRepresentation.getStandardSettings(getSettingsType());

    for (Map.Entry<CodeStyleSettingRepresentation.SettingsGroup, Set<CodeStyleSettingRepresentation>> entry: settingsMap.entrySet()) {
      String groupName = entry.getKey().name;
      for (CodeStyleSettingRepresentation setting: entry.getValue()) {
        initBooleanField(setting.getFieldName(), setting.getUiName(), groupName);
      }
      initCustomOptions(groupName);
    }
  }

  @Override
  protected String getTabTitle() {
    return ApplicationBundle.message("title.spaces");
  }
}
