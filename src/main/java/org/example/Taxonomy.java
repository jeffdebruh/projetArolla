package org.example;

import java.util.*;

public class Taxonomy {
    String name;
    List<Taxonomy> subCategories;

    public Taxonomy(String name, List<Taxonomy> subCategories) {
        this.name = name;
        this.subCategories = subCategories;
    }

    public static void printCategory(Taxonomy category, int indent) {
        for (int i = 0; i < indent; i++) System.out.print("    ");
        System.out.println("- " + category.name);

        if (category.subCategories != null) {
            for (Taxonomy subCategory : category.subCategories) {
                printCategory(subCategory, indent + 1);
            }
        }
    }

    private static void runQuestionnaire(Taxonomy category, List<HashMap<String, String>> exploredCategories) {
        while (true) {
            displaySubCategories(category);

            int index2 = readUserChoice(category.subCategories.size());

            if (index2 == -1) {
                System.out.println("Sortie de la catégorie");
                break;
            } else if (index2 == -2){
                System.out.println("Arrêt du questionnaire.");
                System.out.println(exploredCategories);
                System.exit(0);
            }

            Taxonomy selectedCategory = category.subCategories.get(index2);
            System.out.println("Vous avez sélectionné : " + selectedCategory.name);

            if (selectedCategory.subCategories == null || selectedCategory.subCategories.isEmpty()) {
                exploreItems(selectedCategory, exploredCategories);
            } else {
                if (promptYesNo("Voulez-vous explorer les compétences de cette catégorie ?")) {
                    runQuestionnaire(selectedCategory, exploredCategories);
                }
            }

            if (!promptYesNo("Voulez-vous continuer avec d'autres sous-catégories ?")) {
                System.out.println("Arrêt du questionnaire.");
                break;
            }
        }

        System.out.println("Catégories explorées : " + exploredCategories);
    }

    private static void exploreItems(Taxonomy category, List<HashMap<String, String>> exploredCategories) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Veuillez entrer votre niveau pour " + category.name + ":");
            String level = scanner.nextLine();

            HashMap<String, String> map = new HashMap<>();
            map.put(category.name, level);
            exploredCategories.add(map);

            System.out.println("Compétence et niveau ajoutés : " + map);

            if (!promptYesNo("Voulez-vous continuer avec d'autres compétences ?")) {
                break;
            }
        }
    }

    private static void displaySubCategories(Taxonomy category) {
        int c = 1;
        System.out.println("Sous-catégories disponibles de " + category.name + ":");
        for (Taxonomy item : category.subCategories) {
            System.out.println(c + ". " + item.name);
            c++;
        }
    }

    private static int readUserChoice(int maxChoice) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Veuillez taper le numéro correspondant à votre choix (ou tapez '0' pour sortir de la catégorie et 'stop' pour arreter le questionnaire):");
            String input = scanner.nextLine();

            try {
                int choice = Integer.parseInt(input);
                if (choice == 0) {
                    return -1;
                } else if (choice >= 1 && choice <= maxChoice) {
                    return choice - 1;
                } else {
                    System.out.println("Numéro invalide. Veuillez entrer un nombre valide.");
                }
            } catch (NumberFormatException e) {
                if (input.equalsIgnoreCase("stop")) {
                    return -2;
                } else {
                    System.out.println("Entrée invalide. Veuillez entrer un nombre.");
                }
            }
        }
    }

    private static boolean promptYesNo(String message) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(message + " (oui/non)");
            String response = scanner.nextLine().toLowerCase();

            if (response.equals("oui") || response.equals("o")) {
                return true;
            } else if (response.equals("non") || response.equals("n")) {
                return false;
            } else {
                System.out.println("Réponse invalide. Veuillez répondre par 'oui' ou 'non'.");
            }
        }
    }

    public static void main(String[] args) {
        Taxonomy langagesDeProgrammationEtTechnologies = new Taxonomy("Langages de Programmation et Technologies", Arrays.asList(
                new Taxonomy("Backend", Arrays.asList(
                        new Taxonomy("Java", null),
                        new Taxonomy("Spring", null),
                        new Taxonomy("SpringBoot", null),
                        new Taxonomy("Hibernate", null),
                        new Taxonomy("Go", null),
                        new Taxonomy("C#", null),
                        new Taxonomy("ASP.NET Core", null),
                        new Taxonomy("ASP.NET Framework", null),
                        new Taxonomy("Python", null),
                        new Taxonomy("SQL", null)
                )),
                new Taxonomy("Frontend", Arrays.asList(
                        new Taxonomy("Angular", null),
                        new Taxonomy("React", null),
                        new Taxonomy("TypeScript", null),
                        new Taxonomy("JavaScript", null),
                        new Taxonomy("HTML", null),
                        new Taxonomy("CSS", null)
                )),
                new Taxonomy("Databases", Arrays.asList(
                        new Taxonomy("SQL Server", null),
                        new Taxonomy("MongoDB", null),
                        new Taxonomy("PostgreSQL", null),
                        new Taxonomy("Elasticsearch", null)
                )),
                new Taxonomy("DevOps & Cloud", Arrays.asList(
                        new Taxonomy("Docker", null),
                        new Taxonomy("Kubernetes", null),
                        new Taxonomy("AWS", null),
                        new Taxonomy("Azure", null)
                )),
                new Taxonomy("Messaging & Integration", Arrays.asList(
                        new Taxonomy("Kafka", null),
                        new Taxonomy("Quarkus", null),
                        new Taxonomy("Pub/Sub", null)
                )),
                new Taxonomy("Frameworks & Libraries", Arrays.asList(
                        new Taxonomy("Quarkus", null),
                        new Taxonomy("JPA", null),
                        new Taxonomy("TypeORM", null),
                        new Taxonomy("Express", null)
                )),
                new Taxonomy("Testing", Arrays.asList(
                        new Taxonomy("TDD", null),
                        new Taxonomy("BDD", null),
                        new Taxonomy("Jest", null),
                        new Taxonomy("Mockito", null),
                        new Taxonomy("JUnit", null)
                ))
        ));

        Taxonomy architectureEtConception = new Taxonomy("Architecture et Conception", Arrays.asList(
                new Taxonomy("Architecture Logicielle", Arrays.asList(
                        new Taxonomy("Microservices", null),
                        new Taxonomy("Architecture Hexagonale", null),
                        new Taxonomy("DDD (Domain-Driven Design)", null)
                )),
                new Taxonomy("Design Patterns", Arrays.asList(
                        new Taxonomy("Event Sourcing", null),
                        new Taxonomy("Enterprise Integration Patterns", null)
                )),
                new Taxonomy("Infrastructure", Arrays.asList(
                        new Taxonomy("Cloud (AWS, Azure)", null),
                        new Taxonomy("Infrastructure as Code (IaC)", null)
                ))
        ));

        Taxonomy methodologiesEtPratiques = new Taxonomy("Méthodologies et Pratiques", Arrays.asList(
                new Taxonomy("Agile & Lean", Arrays.asList(
                        new Taxonomy("Scrum", null),
                        new Taxonomy("Kanban", null),
                        new Taxonomy("Agile/Lean", null)
                )),
                new Taxonomy("Craftsmanship", Arrays.asList(
                        new Taxonomy("Clean Code", null),
                        new Taxonomy("xDD (TDD, BDD, DDD)", null),
                        new Taxonomy("Coding Games", null),
                        new Taxonomy("Pair/Mob Programming", null)
                )),
                new Taxonomy("Problem Solving & Complexity", Arrays.asList(
                        new Taxonomy("Problem Solving", null),
                        new Taxonomy("Complexité", null),
                        new Taxonomy("Frugalité", null)
                ))
        ));

        Taxonomy competencesTransversalesEtSoftSkills = new Taxonomy("Compétences Transversales et Soft Skills", Arrays.asList(
                new Taxonomy("Leadership & Management", Arrays.asList(
                        new Taxonomy("Leadership", null),
                        new Taxonomy("Gestion d'équipe", null)
                )),
                new Taxonomy("Communication & Transmission", Arrays.asList(
                        new Taxonomy("Communication", null),
                        new Taxonomy("Partage de connaissances", null),
                        new Taxonomy("Rédaction d'articles", null),
                        new Taxonomy("Mentoring", null),
                        new Taxonomy("Training", null)
                )),
                new Taxonomy("Adaptabilité & Apprentissage", Arrays.asList(
                        new Taxonomy("Adaptation", null),
                        new Taxonomy("Apprentissage continu", null),
                        new Taxonomy("Curiosité", null)
                ))
        ));

        Taxonomy securiteInformatique = new Taxonomy("Sécurité Informatique", Arrays.asList(
                new Taxonomy("Pentesting & CTF", Arrays.asList(
                        new Taxonomy("Pentest", null),
                        new Taxonomy("Capture the Flag (CTF)", null)
                )),
                new Taxonomy("Cybersécurité", Arrays.asList(
                        new Taxonomy("Sécurité des applications", null),
                        new Taxonomy("Vulgarisation de concepts techniques", null)
                ))
        ));

        Taxonomy specialitesEtRoles = new Taxonomy("Spécialités et Rôles", Arrays.asList(
                new Taxonomy("Développement Backend", Arrays.asList(
                        new Taxonomy("Java", null),
                        new Taxonomy("C#", null),
                        new Taxonomy("Python", null)
                )),
                new Taxonomy("Développement Full Stack", Arrays.asList(
                        new Taxonomy("Java/Angular", null),
                        new Taxonomy("ASP.NET/React", null)
                )),
                new Taxonomy("Développement Frontend", Arrays.asList(
                        new Taxonomy("React", null),
                        new Taxonomy("Angular", null)
                )),
                new Taxonomy("DevOps", Arrays.asList(
                        new Taxonomy("Infrastructure/ops", null),
                        new Taxonomy("Cloud Architecture", null)
                )),
                new Taxonomy("Consulting & Coaching", Arrays.asList(
                        new Taxonomy("Coach agile", null),
                        new Taxonomy("Consultant technique", null),
                        new Taxonomy("Formateur", null),
                        new Taxonomy("Speaker", null)
                ))
        ));

        Taxonomy environnementsEtOutils = new Taxonomy("Environnements et Outils", Arrays.asList(
                new Taxonomy("Environnements de Développement", Arrays.asList(
                        new Taxonomy("Windows", null),
                        new Taxonomy("Ubuntu", null),
                        new Taxonomy("Git", null),
                        new Taxonomy("GitHub", null),
                        new Taxonomy("GitLab CI", null),
                        new Taxonomy("GitHub Actions", null)
                )),
                new Taxonomy("Outils de Collaboration et de Suivi", Arrays.asList(
                        new Taxonomy("JIRA", null),
                        new Taxonomy("User Story", null),
                        new Taxonomy("SCRUM", null)
                ))
        ));

        Taxonomy rootCategory = new Taxonomy("Root", Arrays.asList(
                langagesDeProgrammationEtTechnologies,
                architectureEtConception,
                methodologiesEtPratiques,
                competencesTransversalesEtSoftSkills,
                securiteInformatique,
                specialitesEtRoles,
                environnementsEtOutils
        ));

        List<HashMap<String, String>> allItems = new ArrayList<>();
        runQuestionnaire(rootCategory, allItems);
        System.out.println(allItems);
    }
}
