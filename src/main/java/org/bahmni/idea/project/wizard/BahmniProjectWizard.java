package org.bahmni.idea.project.wizard;

import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.roots.ContentEntry;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.File;

public class BahmniProjectWizard extends ModuleBuilder {

	private static final Logger LOG = Logger.getInstance("#org.bahmni.idea.project.wizard.BahmniProjectWizard");

	@Override
	public void setupRootModel(ModifiableRootModel modifiableRootModel) throws ConfigurationException {
		ContentEntry contentEntry = doAddContentEntry(modifiableRootModel);
		if(contentEntry != null){
			final String path = getContentEntryPath() + File.separator + "openmrs";
			new File(path).mkdirs();
			final VirtualFile sourceRoot = LocalFileSystem.getInstance().refreshAndFindFileByPath(FileUtil.toSystemIndependentName(path));
			if (sourceRoot != null) {
				contentEntry.addSourceFolder(sourceRoot, false);
			}
		}
	}

	@Override
	public ModuleWizardStep[] createWizardSteps(@NotNull WizardContext wizardContext,
	                                            @NotNull ModulesProvider modulesProvider) {
		return new ModuleWizardStep[]{new ModuleWizardStep() {
			@Override
			public JComponent getComponent() {
				return new JLabel("Put your content here");
			}

			@Override
			public void updateDataModel() {

			}
		}};
	}


	@Override
	public ModuleType getModuleType() {
		return ModuleType.EMPTY;
	}
}
