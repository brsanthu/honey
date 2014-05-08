package com.brsanthu.honey.handler;
import static com.brsanthu.eclipseutils.EclipseUtils.getTextSelection;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.TextViewerDeleteLineTarget;

import com.brsanthu.eclipseutils.EclipseUtils;

public class HoneyEditor {
    
    private IEditorPart editor = null;
    private ITextEditor textEditor = null;
    private IDocument textDocument = null;
    
    public HoneyEditor(IEditorPart editor) {
        super();
        this.editor = editor;
        if (editor instanceof ITextEditor) {
        	textEditor = (ITextEditor) editor;
        }
        textDocument = getDocument();
    }
    
    public void selectAll() {
        textEditor.setHighlightRange(0, textDocument.getLength(), false);
    }
    
    public Boolean setContents(String value) {
        textDocument.set(value);
        return true;
    }
    
    public String getContents() {
        return textDocument.get();
    }
    
    public String getLine(int lineNumber) {
        try {
            return textDocument.get(textDocument.getLineOffset(lineNumber), textDocument.getLineLength(lineNumber));
        } catch (BadLocationException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Boolean replaceSelection(String newText) {
        ITextSelection selection = getSelection();
        
        //Don't process the replace if 
        //  current selection is null,
        //  or if currently nothing selected and nothing being added
        //  or if nothing really changed.
        if (selection == null || (isEmpty(selection.getText()) && isEmpty(newText)) || selection.getText().equals(newText)) {
            return false;
        }

        try {
            textDocument.replace(selection.getOffset(), selection.getLength(), newText);
            ((ITextEditor) editor).selectAndReveal(selection.getOffset(), isEmpty(newText)?0:newText.length());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        
        return true;
    }
    
    public IDocumentProvider getDocumentProvider() {
        if (editor instanceof ITextEditor) {
            return ((ITextEditor) editor).getDocumentProvider();
        }

        IDocumentProvider docProvider =
                (IDocumentProvider) editor.getAdapter(IDocumentProvider.class);
        return docProvider;
    }
    
    public IDocument getDocument() {
        IDocumentProvider provider = getDocumentProvider();
        if (provider != null) {
            return provider.getDocument(editor.getEditorInput());
        }
        
        return null;
    }
    
    public Boolean isTextSelected() {
        return getSelectedText() != null;
    }
    
    public String getSelectedText() {
        ITextSelection selection = getSelection();
        return (selection != null?selection.getText():null);
    }
    
    public int getCurrentLineNumber() {
        TextSelection textSelection = getTextSelection();
        if (textSelection != null) {
            return textSelection.getStartLine() + 1;
        }
        
        return 0;
    }
    
    public ITextSelection getSelection() {
        ISelectionProvider selProvider = getSelectionProvider();
        if (selProvider == null) {
            return null;
        }
        
        ISelection selection = selProvider.getSelection();
        if (selection instanceof ITextSelection) {
            return (ITextSelection) selection;
        }
        
        return null;
    }
    
    public ISelectionProvider getSelectionProvider() {
        if (editor instanceof ITextEditor) {
            return ((ITextEditor) editor).getSelectionProvider();
        }
        if (editor instanceof ISelectionProvider) {
            return (ISelectionProvider) editor;
        }
        Object adapter = editor.getAdapter(ISelectionProvider.class);
        if(adapter instanceof ISelectionProvider){
            return (ISelectionProvider) adapter;
        }
        return null;
    }
    
    public String getFileName() {
        IEditorInput editorInput = editor.getEditorInput();
        if (editorInput instanceof FileEditorInput) {
            return ((FileEditorInput) editorInput).getFile().getName();
        }
        
        return null;
    }
    
    public String getFileNameWithPath() {
        return EclipseUtils.getFileNameWithPath(editor.getEditorInput());
    }
    
    public IResource getResource() {
        return (IResource) editor.getEditorInput().getAdapter(IFile.class);
    }

//	public void getDeleteLine(int lineNumber) {
//        try {
//        	if (textEditor instanceof AbstractTextEditor) {
//        		textEditor.gets
//        	}
//        	
//        	new TextViewerDeleteLineTarget(textEditor)
//        	textEditor.getAdapter(getClass())
//        	Object adapter = editor.getAdapter(TextViewerDeleteLineTarget.class);
//            //return textDocument.get(textDocument.getLineOffset(lineNumber), textDocument.getLineLength(lineNumber))
//        } catch (BadLocationException e) {
//            e.printStackTrace();
//            //return null;
//        }
//	}
}
