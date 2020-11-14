package com.desapet.tccv4;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.desapet.tccv4.config.ConfiguracaoFirebase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Configurar a barra navegation
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Criar área visivel do nav drawer
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        //Cria a área de navegação
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // configurações do nav drawer
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_slideshow,R.id.nav_meusprodutos,R.id.nav_criarproduto)
                .setDrawerLayout(drawer)
                .build();
        //Area de carregamento do conteúdo do fragment
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //Botao up navigation
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        //Navegacao do menu
        NavigationUI.setupWithNavController(navigationView, navController);

    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}