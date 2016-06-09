package org.bahmni.idea;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadCurrentFile extends AnAction {

	@Override
	public void actionPerformed(AnActionEvent e) {

		System.out.println("Hello World");

		final Project project = e.getProject();

		if(project == null){
			return;
		}

		Editor editor = FileEditorManager.getInstance(project).getSelectedTextEditor();
		if(editor == null){
			return;
		}

		final Document document = editor.getDocument();
		if(document == null){
			return;
		}

		VirtualFile virtualFile = FileDocumentManager.getInstance().getFile(document);
		if(virtualFile == null){
			return;
		}

		final String contents;

		try{
			BufferedReader bufferedReader = new BufferedReader(new FileReader(virtualFile.getPath()));
			String currentLine;
			StringBuilder builder = new StringBuilder();
			while((currentLine = bufferedReader.readLine()) != null){
				builder.append(currentLine);
				builder.append("\n");
			}
			contents = builder.toString();
			invokeApp(project,document,contents);
		}
		catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void invokeApp(final Project project,final Document document,final String contents){
		ApplicationManager.getApplication().invokeLater(new Runnable() {
			@Override
			public void run() {
				CommandProcessor.getInstance().executeCommand(project, new Runnable(){
					@Override
					public void run() {
						ApplicationManager.getApplication().runWriteAction(new Runnable(){
							@Override
							public void run() {
								document.setText(contents);
							}
						});
					}
				},"DiskRead",null);
			}
		});
	}
}
