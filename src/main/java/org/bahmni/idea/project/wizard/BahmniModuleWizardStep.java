package org.bahmni.idea.project.wizard;

import com.intellij.ide.util.projectWizard.ModuleWizardStep;

import javax.swing.*;

public class BahmniModuleWizardStep extends ModuleWizardStep{

	@Override
	public JComponent getComponent() {
		return new JLabel("Provide some setting here");
	}

	@Override
	public void updateDataModel() {

	}
}
