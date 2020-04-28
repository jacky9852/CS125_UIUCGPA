package com.example.cs125_uiucgpa;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

public class AddClassDialogue extends AppCompatDialogFragment {
    private EditText editCourseId;
    private AddClassDialogListerner listener;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_add_class_dialogue, null);
        editCourseId = view.findViewById(R.id.edit_courseid);

        builder.setView(view).setTitle("Enter Course ID").setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.applyTexts("cancelled");
            }
        }).setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String courseId = editCourseId.getText().toString();
                listener.applyTexts(courseId);
            }
        });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (AddClassDialogListerner) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "msut implement AddClassDialogListener");
        }
    }

    public interface AddClassDialogListerner {
        void applyTexts(String setCourseId);

    }
}
