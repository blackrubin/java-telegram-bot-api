package io.github.pengrad.openshift;

import static spark.Spark.*;

import io.github.rubinsoft.bot.fruitmasterbot.FruitMasterBot;
import io.github.rubinsoft.bot.librogame.LibrogameBot;
import io.github.rubinsoft.bot.librogame.StoryLoader;
import io.github.rubinsoft.bot.librogame.StoryLoaderHelp;
import io.github.rubinsoft.bot.librogame.StoryUploader;
import io.github.rubinsoft.bot.rpgdice.RPGDiceBot;
import io.github.rubinsoft.bot.wimpb.BrainLoader;
import io.github.rubinsoft.bot.wimpb.FileUploadServlet;
import io.github.rubinsoft.bot.wimpb.WorkIsMyPrisonBot;

public class Main {
    public static void main(String[] args) {
        ipAddress(args[0]);
        port(Integer.parseInt(args[1]));
//        new RefreshBot();
        
        // Bot handler
        post("/myfmb", new FruitMasterBot()); 
        post("/mywimpb", new WorkIsMyPrisonBot());  
        post("/myrpgdice", new RPGDiceBot());
        post("/mylgb", new LibrogameBot());
        
        // GET handler
        get("/webconsole", new WebConsole());
        get("/brainloader", new BrainLoader());
        get("/storyloader", new StoryLoader()); 
        get("/storyloaderhelp", new StoryLoaderHelp()); 
        get("/", new Homepage());

        // POST handler
        post("/upload", new FileUploadServlet());
        post("/uploadStory", new StoryUploader());
        
    }

}
