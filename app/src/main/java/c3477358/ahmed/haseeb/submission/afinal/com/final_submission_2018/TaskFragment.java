package c3477358.ahmed.haseeb.submission.afinal.com.final_submission_2018;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.UUID;

public class TaskFragment extends android.support.v4.app.Fragment {

    private static final String ARG_TODO_ID = "todo_id";

    private MainActivity mTodo;
    private EditText mEditTextTitle;
    private Button mButtonDate;
    private CheckBox mCheckBoxIsComplete;


    // Should use the newInstance command to pass required values required to create the arguemnt


    public static TaskFragment newInstance(UUID todoId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TODO_ID, todoId);

        TaskFragment taskFragment = new TaskFragment();
        taskFragment.setArguments(args);
        return taskFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);



        /*
         TaskFragment accessing the intent from the hosting Activity as in the following code snippet
         allows for simple code that works.

        UUID todoId = (UUID) getActivity()
                .getIntent().getSerializableExtra(TodoActivity.EXTRA_TODO_ID);

         The disadvantage: TodoFragment is no longer reusable as it is coupled to Activities whoes
         intent has to contain the todoId.

         Solution: store the todoId in the fragment's arguments bundle.
            See the TodoFragment newInstance(UUID todoId) method.

         Then to create a new fragment, the TodoActivity should call TodoFragment.newInstance(UUID)
         and pass in the UUID it retrieves from its extra argument.

        */

        UUID todoId = (UUID) getArguments().getSerializable(ARG_TODO_ID);

        mTodo = Model.get(getActivity()).getTodo(todoId);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_task, container, false);

        mEditTextTitle = view.findViewById(R.id.todo_title);
        mEditTextTitle.setText(mTodo.getTitles());
        mEditTextTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This line is intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTodo.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This line is intentionally left blank
            }
        });

        mButtonDate = view.findViewById(R.id.todo_date);
        mButtonDate.setText(mTodo.getDate().toString());
        mButtonDate.setEnabled(false);

        mCheckBoxIsComplete = view.findViewById(R.id.todo_complete);
        mCheckBoxIsComplete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("DEBUG **** TodoFragment","called onCheckedChanged");
                mTodo.setComplete(isChecked);
            }
        });

        return view;

    }
}




