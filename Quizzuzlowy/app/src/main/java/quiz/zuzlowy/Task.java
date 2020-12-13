package quiz.zuzlowy;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

public class Task extends AppCompatActivity {

    public void openActivity(Class widok){
        Intent intent=new Intent(this,widok);
        startActivity(intent);
    }
}
