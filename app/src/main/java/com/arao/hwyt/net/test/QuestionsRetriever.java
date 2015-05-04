package com.arao.hwyt.net.test;

import android.app.Activity;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.arao.hwyt.R;
import com.arao.hwyt.model.Answer;
import com.arao.hwyt.model.Comment;
import com.arao.hwyt.model.Filter;
import com.arao.hwyt.model.Language;
import com.arao.hwyt.model.Question;
import com.arao.hwyt.model.User;
import com.arao.hwyt.model.enums.LanguageConstant;
import com.arao.hwyt.model.enums.LanguageLevel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * User: angelromero
 * Date: 09/05/2014
 * Time: 12:28
 */
public class QuestionsRetriever {

    private final static String URL = "http://192.168.0.13:8080/hwyt_server/QuestionServlet";
    private final static String URL2 = "http://192.168.0.13:8080/hwyt_server/UserServlet";

    public static void createRequest(Activity context) {
//        RequestQueue mRequestQueue =  Volley.newRequestQueue(context);
//        mRequestQueue.add(new JsonArrayRequest(URL,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        try {
//                            parseJSON(response);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        }));
        RequestQueue mRequestQueue =  Volley.newRequestQueue(context);
        mRequestQueue.add(new JsonObjectRequest(URL2, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            parseJSON(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }));
    }

    private static void parseJSON(JSONObject json) throws JSONException {
        Log.d("JSON_RECEIVED", json.toString());
    }

    public static List<Question> retrieveQuestions(Filter filter) {
        List<Question> questions = new ArrayList<Question>();
        String jsonQuestion = "{\"answers_list\":[{\"content\":\"this is the proper answer\",\"user\":{\"mSpokenLanguages\":[{\"mLanguageConstant\":\"ENGLISH\",\"mLanguageLevel\":\"ADVANCED\"}],\"mId\":\"3\",\"mMotherLanguage\":{\"mLanguageConstant\":\"SPANISH\",\"mLanguageLevel\":\"NATIVE_SPEAKER\"},\"mName\":\"antonito\",\"mPassword\":\"pass\",\"mRating\":12,\"mAvatarImage\":2130837593},\"time\":\"May 13, 2014 12:08:10 PM\",\"positive_votes\":2,\"negative_votes\":3}],\"origin_language\":{\"mLanguageConstant\":\"SPANISH\",\"mLanguageLevel\":\"NATIVE_SPEAKER\"},\"destination_langugae\":{\"mLanguageConstant\":\"ENGLISH\",\"mLanguageLevel\":\"UNASSIGNED\"},\"comment_list\":[{\"content\":\"oh yeah\",\"user\":{\"mSpokenLanguages\":[{\"mLanguageConstant\":\"ENGLISH\",\"mLanguageLevel\":\"ADVANCED\"}],\"mId\":\"2\",\"mMotherLanguage\":{\"mLanguageConstant\":\"SPANISH\",\"mLanguageLevel\":\"NATIVE_SPEAKER\"},\"mName\":\"jaimito\",\"mPassword\":\"pass\",\"mRating\":12,\"mAvatarImage\":2130837593},\"positive_votes\":2,\"negative_votes\":3},{\"content\":\"double oh yeah\",\"user\":{\"mSpokenLanguages\":[{\"mLanguageConstant\":\"ENGLISH\",\"mLanguageLevel\":\"ADVANCED\"}],\"mId\":\"2\",\"mMotherLanguage\":{\"mLanguageConstant\":\"SPANISH\",\"mLanguageLevel\":\"NATIVE_SPEAKER\"},\"mName\":\"jaimito\",\"mPassword\":\"pass\",\"mRating\":12,\"mAvatarImage\":2130837593},\"positive_votes\":2,\"negative_votes\":3}],\"content\":\"Why are we here?\",\"user\":{\"mSpokenLanguages\":[{\"mLanguageConstant\":\"ENGLISH\",\"mLanguageLevel\":\"ADVANCED\"}],\"mId\":\"1\",\"mMotherLanguage\":{\"mLanguageConstant\":\"SPANISH\",\"mLanguageLevel\":\"NATIVE_SPEAKER\"},\"mName\":\"pepito\",\"mPassword\":\"pass\",\"mRating\":12,\"mAvatarImage\":2130837593},\"positive_votes\":20,\"negative_votes\":5}";
        String jsonQuestion2 = "{\"answers_list\":[{\"content\":\"this is the proper answer\",\"user\":{\"mSpokenLanguages\":[{\"mLanguageConstant\":\"ENGLISH\",\"mLanguageLevel\":\"ADVANCED\"}],\"mId\":\"3\",\"mMotherLanguage\":{\"mLanguageConstant\":\"SPANISH\",\"mLanguageLevel\":\"NATIVE_SPEAKER\"},\"mName\":\"user\",\"mPassword\":\"pass\",\"mRating\":12,\"mAvatarImage\":2130837594},\"time\":\"May 13, 2014 4:20:47 PM\",\"positive_votes\":2,\"negative_votes\":3,\"id\":0},{\"content\":\"but this is another answer\",\"user\":{\"mSpokenLanguages\":[{\"mLanguageConstant\":\"ENGLISH\",\"mLanguageLevel\":\"ADVANCED\"}],\"mId\":\"2\",\"mMotherLanguage\":{\"mLanguageConstant\":\"SPANISH\",\"mLanguageLevel\":\"NATIVE_SPEAKER\"},\"mName\":\"0982037402374\",\"mPassword\":\"pass\",\"mRating\":12,\"mAvatarImage\":2130837610},\"time\":\"May 13, 2014 4:20:47 PM\",\"positive_votes\":3,\"negative_votes\":8,\"id\":0},{\"content\":\"another answer but this time it will be super big with a lot of stuff on it and so on. You know what I mean\",\"user\":{\"mSpokenLanguages\":[{\"mLanguageConstant\":\"ENGLISH\",\"mLanguageLevel\":\"ADVANCED\"}],\"mId\":\"1\",\"mMotherLanguage\":{\"mLanguageConstant\":\"SPANISH\",\"mLanguageLevel\":\"NATIVE_SPEAKER\"},\"mName\":\"OIUQWEORIUQW\",\"mPassword\":\"pass\",\"mRating\":12,\"mAvatarImage\":2130837609},\"time\":\"May 13, 2014 4:20:47 PM\",\"positive_votes\":5,\"negative_votes\":0,\"id\":0}],\"origin_language\":{\"mLanguageConstant\":\"SPANISH\",\"mLanguageLevel\":\"NATIVE_SPEAKER\"},\"destination_langugae\":{\"mLanguageConstant\":\"ENGLISH\",\"mLanguageLevel\":\"UNASSIGNED\"},\"comment_list\":[{\"content\":\"oh yeah\",\"user\":{\"mSpokenLanguages\":[{\"mLanguageConstant\":\"ENGLISH\",\"mLanguageLevel\":\"ADVANCED\"}],\"mId\":\"2\",\"mMotherLanguage\":{\"mLanguageConstant\":\"SPANISH\",\"mLanguageLevel\":\"NATIVE_SPEAKER\"},\"mName\":\"0982037402374\",\"mPassword\":\"pass\",\"mRating\":12,\"mAvatarImage\":2130837610},\"time\":\"May 13, 2014 4:20:47 PM\",\"positive_votes\":2,\"negative_votes\":3,\"id\":0},{\"content\":\"double oh yeah\",\"user\":{\"mSpokenLanguages\":[{\"mLanguageConstant\":\"ENGLISH\",\"mLanguageLevel\":\"ADVANCED\"}],\"mId\":\"2\",\"mMotherLanguage\":{\"mLanguageConstant\":\"SPANISH\",\"mLanguageLevel\":\"NATIVE_SPEAKER\"},\"mName\":\"0982037402374\",\"mPassword\":\"pass\",\"mRating\":12,\"mAvatarImage\":2130837610},\"time\":\"May 13, 2014 4:20:47 PM\",\"positive_votes\":2,\"negative_votes\":3,\"id\":0}],\"content\":\"How would you translate this into another strange langugage??\",\"user\":{\"mSpokenLanguages\":[{\"mLanguageConstant\":\"ENGLISH\",\"mLanguageLevel\":\"ADVANCED\"}],\"mId\":\"1\",\"mMotherLanguage\":{\"mLanguageConstant\":\"SPANISH\",\"mLanguageLevel\":\"NATIVE_SPEAKER\"},\"mName\":\"OIUQWEORIUQW\",\"mPassword\":\"pass\",\"mRating\":12,\"mAvatarImage\":2130837609},\"time\":\"May 13, 2014 4:20:47 PM\",\"positive_votes\":30,\"negative_votes\":1,\"id\":0}";
        String jsonQuestion3 = "{\"answers_list\":[{\"content\":\"this is the proper answer\",\"user\":{\"mSpokenLanguages\":[{\"mLanguageConstant\":\"ENGLISH\",\"mLanguageLevel\":\"ADVANCED\"}],\"mId\":\"3\",\"mMotherLanguage\":{\"mLanguageConstant\":\"SPANISH\",\"mLanguageLevel\":\"NATIVE_SPEAKER\"},\"mName\":\"user\",\"mPassword\":\"pass\",\"mRating\":12,\"mAvatarImage\":2130837593},\"time\":\"May 13, 2014 4:16:14 PM\",\"positive_votes\":2,\"negative_votes\":3,\"id\":0},{\"content\":\"but this is another answer\",\"user\":{\"mSpokenLanguages\":[{\"mLanguageConstant\":\"ENGLISH\",\"mLanguageLevel\":\"ADVANCED\"}],\"mId\":\"2\",\"mMotherLanguage\":{\"mLanguageConstant\":\"SPANISH\",\"mLanguageLevel\":\"NATIVE_SPEAKER\"},\"mName\":\"asdfasdf\",\"mPassword\":\"pass\",\"mRating\":12,\"mAvatarImage\":2130837603},\"time\":\"May 13, 2014 4:16:14 PM\",\"positive_votes\":3,\"negative_votes\":8,\"id\":0}],\"origin_language\":{\"mLanguageConstant\":\"SPANISH\",\"mLanguageLevel\":\"NATIVE_SPEAKER\"},\"destination_langugae\":{\"mLanguageConstant\":\"ENGLISH\",\"mLanguageLevel\":\"UNASSIGNED\"},\"comment_list\":[{\"content\":\"oh yeah\",\"user\":{\"mSpokenLanguages\":[{\"mLanguageConstant\":\"ENGLISH\",\"mLanguageLevel\":\"ADVANCED\"}],\"mId\":\"2\",\"mMotherLanguage\":{\"mLanguageConstant\":\"SPANISH\",\"mLanguageLevel\":\"NATIVE_SPEAKER\"},\"mName\":\"asdfasdf\",\"mPassword\":\"pass\",\"mRating\":12,\"mAvatarImage\":2130837603},\"time\":\"May 13, 2014 4:16:14 PM\",\"positive_votes\":2,\"negative_votes\":3,\"id\":0},{\"content\":\"double oh yeah\",\"user\":{\"mSpokenLanguages\":[{\"mLanguageConstant\":\"ENGLISH\",\"mLanguageLevel\":\"ADVANCED\"}],\"mId\":\"2\",\"mMotherLanguage\":{\"mLanguageConstant\":\"SPANISH\",\"mLanguageLevel\":\"NATIVE_SPEAKER\"},\"mName\":\"asdfasdf\",\"mPassword\":\"pass\",\"mRating\":12,\"mAvatarImage\":2130837603},\"time\":\"May 13, 2014 4:16:14 PM\",\"positive_votes\":2,\"negative_votes\":3,\"id\":0}],\"content\":\"This is another example of question??\",\"user\":{\"mSpokenLanguages\":[{\"mLanguageConstant\":\"ENGLISH\",\"mLanguageLevel\":\"ADVANCED\"}],\"mId\":\"1\",\"mMotherLanguage\":{\"mLanguageConstant\":\"SPANISH\",\"mLanguageLevel\":\"NATIVE_SPEAKER\"},\"mName\":\"user234982734\",\"mPassword\":\"pass\",\"mRating\":12,\"mAvatarImage\":2130837604},\"time\":\"May 13, 2014 4:16:14 PM\",\"positive_votes\":30,\"negative_votes\":1,\"id\":0}";
        String jsonQuestion4 = "{\"answers_list\":[{\"content\":\"this is the proper answer\",\"user\":{\"mSpokenLanguages\":[{\"mLanguageConstant\":\"ENGLISH\",\"mLanguageLevel\":\"ADVANCED\"}],\"mId\":\"3\",\"mMotherLanguage\":{\"mLanguageConstant\":\"SPANISH\",\"mLanguageLevel\":\"NATIVE_SPEAKER\"},\"mName\":\"antonito\",\"mPassword\":\"pass\",\"mRating\":12,\"mAvatarImage\":2130837606},\"time\":\"May 13, 2014 12:25:21 PM\",\"positive_votes\":2,\"negative_votes\":3,\"id\":0}],\"origin_language\":{\"mLanguageConstant\":\"SPANISH\",\"mLanguageLevel\":\"NATIVE_SPEAKER\"},\"destination_langugae\":{\"mLanguageConstant\":\"ENGLISH\",\"mLanguageLevel\":\"UNASSIGNED\"},\"comment_list\":[{\"content\":\"oh yeah\",\"user\":{\"mSpokenLanguages\":[{\"mLanguageConstant\":\"ENGLISH\",\"mLanguageLevel\":\"ADVANCED\"}],\"mId\":\"2\",\"mMotherLanguage\":{\"mLanguageConstant\":\"SPANISH\",\"mLanguageLevel\":\"NATIVE_SPEAKER\"},\"mName\":\"jaimito\",\"mPassword\":\"pass\",\"mRating\":12,\"mAvatarImage\":2130837606},\"time\":\"May 13, 2014 12:25:21 PM\",\"positive_votes\":2,\"negative_votes\":3,\"id\":0},{\"content\":\"double oh yeah\",\"user\":{\"mSpokenLanguages\":[{\"mLanguageConstant\":\"ENGLISH\",\"mLanguageLevel\":\"ADVANCED\"}],\"mId\":\"2\",\"mMotherLanguage\":{\"mLanguageConstant\":\"SPANISH\",\"mLanguageLevel\":\"NATIVE_SPEAKER\"},\"mName\":\"jaimito\",\"mPassword\":\"pass\",\"mRating\":12,\"mAvatarImage\":2130837606},\"time\":\"May 13, 2014 12:25:21 PM\",\"positive_votes\":2,\"negative_votes\":3,\"id\":0}],\"content\":\"How would you translate another example question into a different language but with a longer text that the others??\",\"user\":{\"mSpokenLanguages\":[{\"mLanguageConstant\":\"ENGLISH\",\"mLanguageLevel\":\"ADVANCED\"}],\"mId\":\"1\",\"mMotherLanguage\":{\"mLanguageConstant\":\"SPANISH\",\"mLanguageLevel\":\"NATIVE_SPEAKER\"},\"mName\":\"otroUser\",\"mPassword\":\"pass\",\"mRating\":12,\"mAvatarImage\":2130837606},\"time\":\"May 13, 2014 12:25:21 PM\",\"positive_votes\":30,\"negative_votes\":1,\"id\":0}";
        Gson gson = new Gson();
        Question question = gson.fromJson(jsonQuestion, Question.class);
        Question question2 = gson.fromJson(jsonQuestion2, Question.class);
        Question question3 = gson.fromJson(jsonQuestion3, Question.class);
        Question question4 = gson.fromJson(jsonQuestion4, Question.class);

        questions.add(question2);
        questions.add(question3);
        questions.add(question4);
        return questions;
    }

    private void initDummyContent() {
        Question question = new Question();
        User user = new User(1, "OIUQWEORIUQW", "pass", new Language(LanguageConstant.SPANISH, LanguageLevel.NATIVE_SPEAKER), Arrays.asList(new Language(LanguageConstant.ENGLISH, LanguageLevel.ADVANCED), new Language(LanguageConstant.FRENCH, LanguageLevel.ADVANCED), new Language(LanguageConstant.GERMAN, LanguageLevel.BEGINNER)), R.drawable.avatar_14, 12);
        User user2 = new User(2, "0982037402374", "pass", new Language(LanguageConstant.SPANISH, LanguageLevel.NATIVE_SPEAKER), Arrays.asList(new Language(LanguageConstant.ENGLISH, LanguageLevel.ADVANCED)), R.drawable.avatar_9, 12);
        User user3 = new User(3, "user", "pass", new Language(LanguageConstant.SPANISH, LanguageLevel.NATIVE_SPEAKER), Arrays.asList(new Language(LanguageConstant.ENGLISH, LanguageLevel.ADVANCED)), R.drawable.avatar_10, 12);
        List<Comment> comments = Arrays.asList(new Comment("oh yeah", user2, new Date(), null, 2, 3),
                new Comment("double oh yeah", user2, new Date(), null, 2, 3));
        List<Answer> answers = Arrays.asList(new Answer("this is the proper answer", user3, new Date(), null, 2, 3),
                new Answer("but this is another answer", user2, new Date(), null, 3, 8),
                new Answer("another answer but this time it will be super big with a lot of stuff on it and so on. You know what I mean", user, new Date(), null, 5, 0));

        question.setContent("How would you translate this into another strange langugage??");
        question.setUser(user);
        question.setTimePosted(new Date());
        question.setComments(comments);
        question.setPositiveVotes(30);
        question.setNegativeVotes(1);
        question.setFromLanguage(new Language(LanguageConstant.SPANISH, LanguageLevel.NATIVE_SPEAKER));
        question.setToLanguage(new Language(LanguageConstant.ENGLISH, LanguageLevel.UNASSIGNED));
        question.setmAnswers(answers);


        Gson gson = new GsonBuilder().create();
        String flat = gson.toJson(question);
        Log.d("QUESTIONS_JSON", flat);

//        ((HwytApplication) getApplication()).setLoggedUser(user);
    }

//    public static void test(Context context) {
//
//        LoginRequest loginRequest = null;
//        try {
//            loginRequest = new LoginRequest("aromero", "pass_aromero", new Response.Listener<User>() {
//                @Override
//                public void onResponse(User response) {
//                    Log.d("USER_ID", Integer.toString(response.getId()));
//                    response.getAvatarImage();
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    NetworkResponse response = error.networkResponse;
//                    if(response != null && response.data != null){
//                        String json = new String(response.data);
//                        json.toString();
//                    }
//                }
//            });
//        } catch (GeneralSecurityException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        RequestQueue mRequestQueue =  Volley.newRequestQueue(context);
//        mRequestQueue.add(loginRequest);
//    }

//    public static void testRegister(Context context) throws GeneralSecurityException {
//        User user2 = new User("testUser4", "password3", new Language(LanguageConstant.SPANISH, LanguageLevel.NATIVE_SPEAKER), Arrays.asList(new Language(LanguageConstant.ENGLISH, LanguageLevel.ADVANCED)));
//        RegisterRequest registerRequest = null;
//        try {
//            user2.setPassword(PasswordEncrypter.encrypt(user2.getPassword()));
//            registerRequest = new RegisterRequest(user2, new Response.Listener<Integer>() {
//                @Override
//                public void onResponse(Integer response) {
//                    Log.d("USER_ID", Integer.toString(response));
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//
//                }
//            });
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//        RequestQueue mRequestQueue =  Volley.newRequestQueue(context);
//        mRequestQueue.add(registerRequest);
//    }
}
