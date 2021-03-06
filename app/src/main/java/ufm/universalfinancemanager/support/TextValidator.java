/* Author: Sean Hansen
* ID: 108841276
* Date Started: 11/3/17
* Date Complete: 11/5/17
* Peer Review:
*   Date:
*   Team Members:
* Contributing Team Members:
*/
package ufm.universalfinancemanager.support;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

/**
 * Created by smh7 on 11/3/17.
 */

public abstract class TextValidator implements TextWatcher {
    private final TextView textView;

    public TextValidator(TextView textView) {
        this.textView = textView;
    }

    public abstract void validate(TextView textView, String text);

    @Override
    final public void afterTextChanged(Editable s) {
        String text = textView.getText().toString();
        validate(textView, text);
    }

    @Override
    final public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    final public void onTextChanged(CharSequence s, int start, int before, int count) {}
}
