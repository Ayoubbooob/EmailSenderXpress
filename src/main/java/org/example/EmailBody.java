package org.example;

public class EmailBody {


    private final String  STATIC_MESSAGE = "J'espère que ce message vous trouve bien. Je me permets de vous contacter afin de vous exprimer mon intérêt pour un stage dans le domaine du développement logiciel, plus particulièrement dans le développement Java avec Spring Boot et les meilleures pratiques DevOps.\n" +
            "\n" +
            "Je suis actuellement étudiant en deuxième année à l'ENSIAS, filière Génie Logiciel, et je suis passionné par le développement d'applications robustes et performantes. Au cours de mes études, j'ai acquis des connaissances approfondies en programmation Java ainsi qu'en utilisation de frameworks tels que Spring Boot.\n" +
            "\n" +
            "En outre, je suis conscient de l'importance des pratiques DevOps dans le cycle de développement logiciel. J'ai une compréhension des concepts DevOps tels que l'intégration continue, le déploiement et l'automatisation des tests. J'ai également une expérience pratique avec des outils tels que Docker, Git, CI/CD.\n" +
            "\n" +
            "J'ai joint mon CV à ce message pour que vous puissiez avoir un aperçu détaillé de mon parcours académique et de mes compétences techniques.\n" +
            "\n" +
            "Je serais ravi de contribuer à votre entreprise et d'apprendre davantage au sein de votre équipe. Je suis disponible pour discuter plus en détail de ma candidature et des opportunités de stage que vous pourriez offrir.\n" +
            "\n" +
            "Je vous remercie sincèrement pour votre attention et j'attends avec impatience votre réponse.\n" +
            "\n" +
            "Cordialement,\n" +
            "Ayoub Boubkrioui.\n";
    private final String companyName;


    public EmailBody(String companyName) {
        this.companyName = companyName;
    }


    public String getCompanyName() {
        return companyName;
    }


    public String setBody(){
        return "Bonjour "+companyName + ",\n" + STATIC_MESSAGE;
    }
}
