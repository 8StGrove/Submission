package c3477358.ahmed.haseeb.submission.afinal.com.final_submission_2018;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

public class Model {

    private static Model sTodoModel;

    private ArrayList<MainActivity> mTodoList;

    public static Model get(Context context) {
        if (sTodoModel == null) {
            sTodoModel = new Model(context);
        }
        return sTodoModel;
    }

    private Model(Context context){
        mTodoList = new ArrayList<>();

        // refactor to pattern for data plugins
        // simulate some data for testing

        for (int i=0; i < 3; i++){
            MainActivity task= new MainActivity();
            task.setTitle("Task title " + i);
            task.setDetail("Detail for task " + task.getId().toString());
            task.setComplete(false);

            mTodoList.add(task);
        }

    }

    public MainActivity getTodo(UUID todoId) {

        for (MainActivity todo : mTodoList) {
            if (todo.getId().equals(todoId)){
                return todo;
            }
        }

        return null;
    }

    public ArrayList<MainActivity> getTodos() {

        return mTodoList;

    }

    public void addTask(MainActivity task){

        mTodoList.add(task);

    }

}
