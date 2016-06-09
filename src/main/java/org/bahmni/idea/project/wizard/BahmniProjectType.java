package org.bahmni.idea.project.wizard;

import com.intellij.icons.AllIcons;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.ModuleTypeManager;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class BahmniProjectType extends ModuleType<BahmniProjectWizard> {
	private static final String ID = "DEMO_MODULE_TYPE";

	protected BahmniProjectType(@NotNull @NonNls String id) {
		super(id);
	}

	public static BahmniProjectType getInstance() {
		return (BahmniProjectType) ModuleTypeManager.getInstance().findByID(ID);
	}

	@NotNull
	@Override
	public BahmniProjectWizard createModuleBuilder() {
		return new BahmniProjectWizard();
	}

	@NotNull
	@Override
	public String getName() {
		return "Bahmni Project";
	}

	@NotNull
	@Override
	public String getDescription() {
		return "Bahmni Project";
	}

	@Override
	public Icon getBigIcon() {
		return AllIcons.General.Information;
	}

	@Override
	public Icon getNodeIcon(@Deprecated boolean isOpened) {
		return AllIcons.General.Information;
	}

	@NotNull
	@Override
	public ModuleWizardStep[] createWizardSteps(@NotNull WizardContext wizardContext,
	                                            @NotNull BahmniProjectWizard moduleBuilder,
	                                            @NotNull ModulesProvider modulesProvider) {
		return super.createWizardSteps(wizardContext, moduleBuilder, modulesProvider);
	}
}
