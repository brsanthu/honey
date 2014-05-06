package com.brsanthu.honey.handler.insert;

import static com.brsanthu.eclipseutils.EclipseUtils.getSelection;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.brsanthu.eclipseutils.widgets.OkCancelDialog;

//This is to fix the bug
public class InsertCustomDateDialog extends OkCancelDialog {
    
    private static final String UNIX_TIME = "Unix Time";

    private static String[] dateFormats = new String[] {
        "M/d/yyyy",
        "MM/dd/yyyy",
        "EEEE, MMM d, yyyy",
        "EEEE, MMM d, yyyy h:m a",
        "EEEE, MMM d, yyyy h:m:s a",
        "MM/dd/yyyy h:m a",
        "MM/dd/yyyy h:m:s a",
        "MMMM d",
        "yyyy-MM-dd'T'hh:mm:ss.SSS",
        "EEE, d MMM, yyyy h:m:s a z",
        "h:m a",
        "h:m:s a",
        "hh:mm",
        "hh:mm:ss",
        UNIX_TIME
    };
    
    private ComboViewer comboViewer;
    private Date date;
    
    /**
     * Create the dialog.
     * @param parent
     */
    public InsertCustomDateDialog(Shell parent) {
        super(parent, "Insert Custom Date", "Please select Date/Time format");
    }
    
    @Override
    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite) super.createDialogArea(parent);
        container.setLayout(new GridLayout(1, false));
        
        comboViewer = new ComboViewer(container, SWT.READ_ONLY);
        Combo combo = comboViewer.getCombo();
        combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        comboViewer.setContentProvider(new ArrayContentProvider());
        
        initDialogArea();
        
        return container;
    }
    
    private void initDialogArea() {
        date = new Date();
        comboViewer.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                if (UNIX_TIME.equals(element)) {
                    return "" + date.getTime();
                }
                
                return new SimpleDateFormat((String) element).format(date);
            }
        });
        comboViewer.setInput(dateFormats);
        comboViewer.setSelection(new StructuredSelection(dateFormats[0]));
    }

    @Override
    protected void okPressed() {
        String dateFormat = (String) getSelection(comboViewer);
        if (UNIX_TIME.equals(dateFormat)) {
            result = "" + date.getTime();
        } else {
            result = new SimpleDateFormat(dateFormat).format(date);
        }
        super.okPressed();
    }
}
