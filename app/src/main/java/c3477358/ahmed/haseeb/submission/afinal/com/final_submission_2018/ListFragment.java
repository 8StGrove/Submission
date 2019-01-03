package c3477358.ahmed.haseeb.submission.afinal.com.final_submission_2018;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {
    private RecyclerView mTodoRecyclerView;
    TodoAdapter mTodoAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);

        mTodoRecyclerView = view.findViewById(R.id.recycler_view);
        mTodoRecyclerView.setLayoutManager( new LinearLayoutManager(getActivity()) );

        updateUI();

        return view;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.new_Task:

                MainActivity mainActivity = new MainActivity();
                Model.get(getActivity()).addTask(mainActivity);

                Intent intent = PagerActivity.newIntent(getActivity(), mainActivity.getId());
                //              Intent intent = TodoActivity.newIntent(getActivity(), todo.getId());
                startActivity(intent);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI(){

        ArrayList todos = new ArrayList<>();
        Model todoModel = Model.get(getContext());
        todos = todoModel.getTodos();

        if (mTodoAdapter == null) {
            mTodoAdapter = new TodoAdapter(todos);
            mTodoRecyclerView.setAdapter(mTodoAdapter);
        } else {
            mTodoAdapter.notifyDataSetChanged();
        }

    }

    public class TodoHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private MainActivity mTodo;
        private TextView mTextViewTitle;
        private TextView mTextViewDate;

        public TodoHolder(LayoutInflater inflater, ViewGroup parent) {

            super(inflater.inflate(R.layout.list_item_todo, parent, false));

            itemView.setOnClickListener(this);

            mTextViewTitle = (TextView) itemView.findViewById(R.id.todo_title);
            mTextViewDate = (TextView) itemView.findViewById(R.id.todo_date);

        }

        @Override
        public void onClick(View view) {
            // have a Toast for now
            Toast.makeText(
                    getActivity(),
                    mTodo.getTitles() + " clicked",
                    Toast.LENGTH_SHORT)
                    .show();

//            Intent intent = TodoActivity.newIntent(getActivity(), mTodo.getId());
            Intent intent = PagerActivity.newIntent(getActivity(), mTodo.getId());

            startActivity(intent);

        }

        public void bind(Todo todo){
            mTodo = todo;
            mTextViewTitle.setText(mTodo.getTitle());
            mTextViewDate.setText(mTodo.getDate().toString());
        }

    }

    public class TodoAdapter extends RecyclerView.Adapter<TodoListFragment.TodoHolder> {

        private List<Todo> mTodos;

        public TodoAdapter(List<Todo> todos) {
            mTodos = todos;
        }

        @Override
        public TodoListFragment.TodoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new TodoHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(TodoHolder holder, int position) {
            Todo todo = mTodos.get(position);
            holder.bind(todo);
        }

        @Override
        public int getItemCount() {
            return mTodos.size();
        }

    }

}
