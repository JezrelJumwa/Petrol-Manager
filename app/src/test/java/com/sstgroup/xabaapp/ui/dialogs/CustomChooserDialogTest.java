package com.sstgroup.xabaapp.ui.dialogs;

import com.sstgroup.xabaapp.models.Program;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by julianlubenov on 7/3/17.
 */
public class CustomChooserDialogTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void getSelectedPrograms_returnsStringContainingNames() throws Exception {
        ArrayList<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        String result = CustomChooserDialog.getSelectedPrograms(list);
        Assert.assertTrue("The result string does not contain the string " + "one", result.contains("one"));
        Assert.assertTrue("The result string does not contain the string " + "two", result.contains("two"));
    }

    @Test
    public void getSelectedProgramsFromObjects_returnsStringContainingObjectNames() throws Exception {
        ArrayList<Program> list = new ArrayList<>();
        list.add(new Program(0L, "one", Program.STATUS_ACTIVE));
        list.add(new Program(0L, "two", Program.STATUS_ACTIVE));
        String result = CustomChooserDialog.getSelectedProgramsFromObjects(list);
        Assert.assertTrue("The result string does not contain the string " + "one", result.contains("one"));
        Assert.assertTrue("The result string does not contain the string " + "two", result.contains("two"));
    }

}