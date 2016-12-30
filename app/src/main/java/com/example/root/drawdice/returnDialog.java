package com.example.root.drawdice;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

//Dialogilla tehty tooltip korttiasetuksiin korttien palautuksen valitsemiselle
public class returnDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.returnTip)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        dialog.dismiss();
                    }
                });
        return builder.create();
    }
}