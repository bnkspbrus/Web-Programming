package ru.itmo.web.lesson4.util;

import ru.itmo.web.lesson4.model.Post;
import ru.itmo.web.lesson4.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static ru.itmo.web.lesson4.model.Color.*;

public class DataUtil {
    private static final List<User> USERS = Arrays.asList(new User(1, "MikeMirzayanov", "Mike Mirzayanov", RED),
            new User(6, "pashka", "Pavel Mavrin", GREEN), new User(9, "geranazarov555", "Georgiy Nazarov", BLUE),
            new User(11, "tourist", "Gennady Korotkevich", GREEN), new User(13, "Vovuh", "Vladimir Petrov", BLUE));
    private static final Random random = new Random();

    private static final List<Post> POSTS = Arrays.asList(
            new Post(random.nextInt(100), USERS.get(0).getId(), "Codeforces Round #748 (Div. 3)",
                    "Привет, Codeforces!\n" + "\n" + "Рад пригласить Вас на увлекательный (а мы постарались его " +
                            "сделать таким) Codeforces Round #748 (Div. 3) — раунд для третьего дивизиона, который " + "состоится в среда, 13 октября 2021 г. в 17:35. Это раунд, сделанный мной (MrPaul_TUser)," + " существенный вклад в его создание которого также внесли MikeMirzayanov и BledDest.\n" + "\n" + "Этот раунд содержит 6-8 задач. Задачи подобраны по сложности так, чтобы составить интересное соревнование для участников с рейтингами до 1600. Однако все желающие, чей рейтинг 1600 и выше, могут зарегистрироваться на раунд вне конкурса."),
            new Post(random.nextInt(100), USERS.get(1).getId(), "Mirror of Bubble Cup 14 Finals on Codeforces",
                    "Hello, Codeforces!\n" + "\n" + "Microsoft Development Center Serbia is thrilled to announce the "
                            + "finals of the 14th edition of Bubble Cup competition! Bubble Cup is an international, "
                            + "ICPC-style team contest aimed at university and high school students."),
            new Post(random.nextInt(100), USERS.get(2).getId(),
                    "COMPFEST 13 Finals Mirror (Unrated, ICPC Rules, Teams Preferred)",
                    "The duration will be 5 hours, consisting of 13 problems. You can register individually, but " +
                            "teams are preferred. You may expect relatively easier problems than ICPC Regional " +
                            "Contests. You may code parallely with several computers with your teammates and use " +
                            "prewritten codes and templates.\n" + "\n" + "COMPFEST itself is an annual event hosted " + "by Universitas Indonesia. It is the largest student-run IT event in Indonesia and " + "competitive programming contest is one of the competitions hosted.\n" + "\n" + "We've " + "put great effort into preparing this contest and we hope that you will enjoy it. See " + "you! ありがとう~! \uD83D\uDC3E"),
            new Post(random.nextInt(100), USERS.get(3).getId(), "ICPC WF Moscow Invitational Contest — Online Mirror",
                    "Hello, everybody!\n" + "\n" + "We would like to invite you to participate in the Mirror of The " + "ICPC World Finals Moscow Invitational Contest. The original contest was made for the " + "teams who cannot come to The World Finals in Moscow. They were competing for the medals " + "and glory."));

//    static {
//        POSTS.sort(new Comparator<Post>() {
//            @Override
//            public int compare(Post o1, Post o2) {
//                return Long.compare(o2.getId(), o1.getId());
//            }
//        });
//    }

    public static void addData(HttpServletRequest request, Map<String, Object> data) {
        data.put("users", USERS);
        data.put("posts", POSTS);
        for (User user : USERS) {
            if (Long.toString(user.getId()).equals(request.getParameter("logged_user_id"))) {
                data.put("user", user);
            }
        }
    }
}
