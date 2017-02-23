package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.mongodb.DBCollection;
import com.mygdx.game.Mnemonica;
import com.mygdx.game.User;

/**
 * Created by user on 4.2.2017.
 */
public class Register_screen extends ScreenAdapter{

    public Mnemonica game;

    public User newUser;
    public String UserName=null;
    public String UserMail;
    public String UserPassword;

    public Stage reg_stage;

    private Skin skin;
    private TextureAtlas atlas;

    private Group regGroup;
    private BitmapFont main_font;
    private Sprite intro_bg,reg_bg;

    private ImageButton sound_btn,register_btn;
    private TextField f_name,f_mail,f_password;

    private TextField.TextFieldStyle styleT;
    private Texture background;

    private Firebase mRef;
    private Firebase mRef2;

    public Register_screen(final Mnemonica game) {
        this.game = game;
        newUser=new User();

        DBCollection userTB = Mnemonica.MDb.getCollection("Users");

        reg_stage = new Stage(Mnemonica.view, Mnemonica.batch);
        atlas = new TextureAtlas(Gdx.files.internal("smth.atlas"));
        skin = new Skin();
        skin.addRegions(atlas);

        //reg_bg = new Sprite(atlas.createSprite("login"));
        background=new Texture(Gdx.files.internal("register.jpg"));

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Cartoon.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 80;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 3;
        float densityIndependentSize = (parameter.size) * Gdx.graphics.getDensity();
        int fontSize = Math.round(densityIndependentSize);
        parameter.size = fontSize;
        main_font = generator.generateFont(parameter);
        generator.dispose();



        styleT = new TextField.TextFieldStyle(main_font, Color.WHITE, null, null, null);

        f_name = new TextField("Name", styleT);
        f_name.setSize((Mnemonica.WIDTH), f_name.getMinHeight());
        f_name.setPosition((Mnemonica.WIDTH/100)*50,(Mnemonica.HEIGHT/100)*55);

        f_mail = new TextField("Mail Address", styleT);
        f_mail.setSize((Mnemonica.WIDTH), f_mail.getMinHeight());
        f_mail.setPosition((Mnemonica.WIDTH/100)*50,(Mnemonica.WIDTH/100)*40);

        f_password = new TextField("Password", styleT);
        f_password.setSize((Mnemonica.WIDTH), f_password.getMinHeight());
        f_password.setPosition((Mnemonica.WIDTH/100)*50,(Mnemonica.WIDTH/100)*25);


        f_name.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Input.TextInputListener textListener1 = new Input.TextInputListener() {
                    //SoundAssets.playSound(SoundAssets.clickSound);

                    @Override
                    public void input(String input) {
                        UserName = input;
                        newUser.setName(UserName);
                        f_name.setText(UserName);
                        f_name.setSize((Mnemonica.WIDTH), f_name.getMinHeight());
                        f_name.setPosition((Mnemonica.WIDTH/100)*50,(Mnemonica.WIDTH/100)*55);

                    }

                    @Override
                    public void canceled() {
                    }

                };
                Gdx.input.getTextInput(textListener1, "Enter your Name", "XX", "");

            }
        });


        f_mail.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Input.TextInputListener textListener2 = new Input.TextInputListener() {
                    @Override
                    public void input(String input) {
                        UserMail = input;
                        newUser.setMail(UserMail);
                        f_mail.setText(UserMail);
                        f_mail.setSize((Mnemonica.WIDTH), f_mail.getMinHeight());
                        f_mail.setPosition((Mnemonica.WIDTH/100)*50,(Mnemonica.WIDTH/100)*40);

                    }

                    @Override

                    public void canceled() {

                    }

                };
                Gdx.input.getTextInput(textListener2, "Enter your mail", "yy@smth.com", "");
            }


        });

        f_password.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Input.TextInputListener textListener2 = new Input.TextInputListener() {
                    @Override
                    public void input(String input) {
                        UserPassword = input;
                        String stars = "";
                        for (int x = 0; x < UserPassword.length(); x++)
                            stars = "*" + stars;
                        f_password.setText(stars);
                        f_password.setSize((Mnemonica.WIDTH), f_password.getMinHeight());
                        f_password.setPosition((Mnemonica.WIDTH/100)*50,(Mnemonica.WIDTH/100)*25);

                    }

                    @Override

                    public void canceled() {

                    }

                };
                Gdx.input.getTextInput(textListener2, "Enter password", "1234", "");
            }
        });


        //NEXT BUTTON

        register_btn = new ImageButton(skin.getDrawable("arrow"));


        register_btn.setPosition((Mnemonica.WIDTH/100)*80,(Mnemonica.HEIGHT/100)*20);
        register_btn.setSize((Mnemonica.WIDTH/100)*15,(Mnemonica.HEIGHT/100)*15);
        register_btn.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button)
            {
                if(register_btn.isPressed())
                {
                    //SoundAssets.playSound(SoundAssets.clickSound);
                   // newUser= new User(UserName,null,UserPassword,UserPassword,null);


                    if(UserName!= null){
                        System.out.println("SUCCESS");
                        mRef= new Firebase("https://mnemonica-15b7e.firebaseio.com/");
                        Firebase mRefChild = mRef.push();
                        mRefChild.child("Name").setValue(newUser.getName());
                        mRefChild.child("Email").setValue(newUser.getE_mail());
                        game.setScreen(new Login_screen(game));
                    }



                }

                return false;
            }});

        //databseden okumak icin
        mRef2= new Firebase("https://mnemonica-15b7e.firebaseio.com/-KdcGkyJ97oDBEEoq1hE/Name");

        mRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                System.out.print(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        reg_stage.addActor(f_mail);
        reg_stage.addActor(f_name);
        reg_stage.addActor(f_password);
        reg_stage.addActor(register_btn);

    }
    public void render ( float delta) {
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(reg_stage);
        Gdx.input.setInputProcessor(inputMultiplexer);

        reg_stage.act();

        Mnemonica.batch.begin();

        Mnemonica.batch.draw(background,0,0,Mnemonica.WIDTH,Mnemonica.HEIGHT);
        Mnemonica.batch.end();

        reg_stage.draw();
    }
}
