package com.shiba.chatbox.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatEditText;
import android.text.Html;
import android.text.InputFilter;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shiba.chatbox.models.ChatModel;

public class Chatbox {
    private TextView chatbox;
    private EditText typebox;
    private Activity activity;
    private RelativeLayout relativeLayout;
    ////
    public Chatbox(Activity activity, RelativeLayout relativeLayout, String playerName)
    {
        setActivity(activity);
        setRelativeLayout(relativeLayout);
        setChatbox(playerName);
    }

    private void setChatbox(final String playerName)
    {
        chatbox = new TextView(activity);
        chatbox.setId(203);
        chatbox.setContentDescription("CLICKABLE");
        chatbox.setGravity(Gravity.BOTTOM);
        chatbox.setText("Chat...");
        chatbox.setTextSize(12);
        chatbox.setMaxLines(5);
        chatbox.setBackgroundColor(Color.parseColor("#A9D0F5"));
        final ScrollingMovementMethod scrollingMovementMethod = new ScrollingMovementMethod();
        chatbox.setMovementMethod(scrollingMovementMethod);
        RelativeLayout.LayoutParams chatboxLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        chatboxLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        chatboxLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        chatboxLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        relativeLayout.addView(chatbox,chatboxLayoutParams);

        chatbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chatbox.getContentDescription().equals("CLICKABLE"))
                {
                    setTypebox(playerName);
                    typebox.setVisibility(View.VISIBLE);
                    typebox.requestFocus();
                }
            }
        });
    }
    private void setTypebox(final String playerName)
    {
        typebox = new AppCompatEditText(activity) {
            @Override
            public boolean onKeyPreIme(int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(this.getWindowToken(), 0);
                    typebox.clearFocus();
                    return true;
                }
                return false;
            }
        };
        typebox.setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI);
        typebox.setId(300);
        typebox.setSingleLine(true);
        typebox.setTextSize(12);
        typebox.setMaxLines(1);
        typebox.setVisibility(View.GONE);
        typebox.setFilters(new InputFilter[] {new InputFilter.LengthFilter(70)});
        final RelativeLayout.LayoutParams typeboxLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        typeboxLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        typeboxLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        typeboxLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        relativeLayout.addView(typebox,typeboxLayoutParams);

        final RelativeLayout.LayoutParams chatboxLayoutParams = (RelativeLayout.LayoutParams) chatbox.getLayoutParams();
        chatboxLayoutParams.addRule(RelativeLayout.ABOVE,300);
        chatboxLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,0);

        typebox.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    chatbox.setContentDescription("NOTCLICKABLE");
                    InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                }else {
                    chatbox.setContentDescription("CLICKABLE");
                    typebox.setVisibility(View.GONE);
                    chatboxLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                    InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(typebox.getWindowToken(), 0);
                }
            }
        });

        typebox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //TODO: put to server
                    chatbox.append(Html.fromHtml("<br><font color=#cc0029>"+playerName+"</font>"+": "+typebox.getText()));
                    typebox.setText("");
                    return true;
                }
                return false;
            }
        });
    }

    private void setActivity(Activity activity)
    {
        this.activity=activity;
    }

    private void setRelativeLayout(RelativeLayout relativeLayout)
    {
        this.relativeLayout=relativeLayout;
    }

    public void add(ChatModel[] chatModels)
    {
        for(ChatModel chatModel : chatModels)
        {
            chatbox.append(Html.fromHtml("<br><font color=#cc0029>"+chatModel.getNickName()+"</font>"+"<font color=#000000>("+chatModel.getMessageModel().getClock()+")</font>: "+chatModel.getMessageModel().getMessage()));
        }
    }
}
