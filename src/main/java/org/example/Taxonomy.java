package org.example;

import java.util.*;

public class Taxonomy {
    String name;
    List<Taxonomy> subCategories;
    List<String> items;

    public Taxonomy(String name, List<Taxonomy> subCategories, List<String> items) {
        this.name = name;
        this.subCategories = subCategories;
        this.items = items;
    }

    public static void printCategory(Taxonomy category, int indent) {
        List<String> dupli = new ArrayList<>();
        List<String> allItems = new ArrayList<>();
        for (int i = 0; i < indent; i++) System.out.print("    ");
        System.out.println("- " + category.name);
        if (category.items != null) {
            for (String item : category.items) {
                for (int i = 0; i < indent + 1; i++) System.out.print("    ");
                if (allItems.contains(item)) dupli.add(item);
                allItems.add(item);
                System.out.println("- " + item);
            }
        }
        if (category.subCategories != null) {
            for (Taxonomy subCategory : category.subCategories) {
                printCategory(subCategory, indent + 1);
            }
        }
    }

    private static void runQuestionnaire(Taxonomy category, List<String> exploredCategories) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Voulez-vous explorer la catégorie : " + category.name + "? (oui/non)");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("y") || response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("oui") || response.equalsIgnoreCase("o")) {
            if (category.items != null) {
                for (String item : category.items) {
                    System.out.println("Compétence/Technologie: " + item);
                    exploredCategories.add(item);
                }
            }

            if (category.subCategories != null) {
                for (Taxonomy subCategory : category.subCategories) {
                    runQuestionnaire(subCategory, exploredCategories);
                }
            }
        }
    }

    public static void main(String[] args) {
        Taxonomy langagesDeProgrammationEtTechnologies = new Taxonomy("Langages de Programmation et Technologies", Arrays.asList(
                new Taxonomy("Backend", Arrays.asList(
                        new Taxonomy("Compétences", null, Arrays.asList("Java", "Spring", "SpringBoot", "Hibernate", "Go", "C#", "ASP.NET Core", "ASP.NET Framework", "Python", "SQL")),
                        new Taxonomy("Frameworks", null, Arrays.asList("Spring Boot", "Django", "Flask", "Ruby on Rails", "Laravel", "Express.js"))
                ), null),
                new Taxonomy("Frontend", Arrays.asList(
                        new Taxonomy("Compétences", null, Arrays.asList("Angular", "React", "TypeScript", "JavaScript", "HTML", "CSS")),
                        new Taxonomy("Frameworks", null, Arrays.asList("React.js", "Angular", "Vue.js", "Svelte"))
                ), null),
                new Taxonomy("Databases", Arrays.asList(
                        new Taxonomy("Compétences", null, Arrays.asList("SQL Server", "MongoDB", "PostgreSQL", "Elasticsearch")),
                        new Taxonomy("Types", null, Arrays.asList("SQL (MySQL, PostgreSQL, SQLite, MariaDB)", "NoSQL (MongoDB, Redis, Cassandra, Neo4j, CouchDB)"))
                ), null),
                new Taxonomy("DevOps & Cloud", Arrays.asList(
                        new Taxonomy("Compétences", null, Arrays.asList("Docker", "Kubernetes", "AWS", "Azure")),
                        new Taxonomy("Outils DevOps", null, Arrays.asList("Terraform", "Ansible", "CloudFormation", "Pulumi", "Jenkins", "GitLab CI/CD", "CircleCI", "ArgoCD", "Prometheus", "Grafana"))
                ), null),
                new Taxonomy("Messaging & Integration", Arrays.asList(
                        new Taxonomy("Compétences", null, Arrays.asList("Kafka", "Quarkus", "Pub/Sub"))
                ), null),
                new Taxonomy("Frameworks & Libraries", Arrays.asList(
                        new Taxonomy("Compétences", null, Arrays.asList("Quarkus", "JPA", "TypeORM", "Express"))
                ), null),
                new Taxonomy("Testing", Arrays.asList(
                        new Taxonomy("Compétences", null, Arrays.asList("TDD", "BDD", "Jest", "Mockito", "JUnit"))
                ), null)
        ), null);

        Taxonomy architectureEtConception = new Taxonomy("Architecture et Conception", Arrays.asList(
                new Taxonomy("Architecture Logicielle", Arrays.asList(
                        new Taxonomy("Compétences", null, Arrays.asList("Microservices", "Architecture Hexagonale", "DDD (Domain-Driven Design)"))
                ), null),
                new Taxonomy("Design Patterns", Arrays.asList(
                        new Taxonomy("Compétences", null, Arrays.asList("Event Sourcing", "Enterprise Integration Patterns"))
                ), null),
                new Taxonomy("Infrastructure", Arrays.asList(
                        new Taxonomy("Compétences", null, Arrays.asList("Cloud (AWS, Azure)", "Infrastructure as Code (IaC)"))
                ), null)
        ), null);

        Taxonomy methodologiesEtPratiques = new Taxonomy("Méthodologies et Pratiques", Arrays.asList(
                new Taxonomy("Agile & Lean", Arrays.asList(
                        new Taxonomy("Compétences", null, Arrays.asList("Scrum", "Kanban", "Agile/Lean"))
                ), null),
                new Taxonomy("Craftsmanship", Arrays.asList(
                        new Taxonomy("Compétences", null, Arrays.asList("Clean Code", "xDD (TDD, BDD, DDD)", "Coding Games", "Pair/Mob Programming"))
                ), null),
                new Taxonomy("Problem Solving & Complexity", Arrays.asList(
                        new Taxonomy("Compétences", null, Arrays.asList("Problem Solving", "Complexité", "Frugalité"))
                ), null)
        ), null);

        Taxonomy competencesTransversalesEtSoftSkills = new Taxonomy("Compétences Transversales et Soft Skills", Arrays.asList(
                new Taxonomy("Leadership & Management", Arrays.asList(
                        new Taxonomy("Compétences", null, Arrays.asList("Leadership", "Gestion d'équipe"))
                ), null),
                new Taxonomy("Communication & Transmission", Arrays.asList(
                        new Taxonomy("Compétences", null, Arrays.asList("Communication", "Partage de connaissances", "Rédaction d'articles", "Mentoring", "Training"))
                ), null),
                new Taxonomy("Adaptabilité & Apprentissage", Arrays.asList(
                        new Taxonomy("Compétences", null, Arrays.asList("Adaptation", "Apprentissage continu", "Curiosité"))
                ), null)
        ), null);

        Taxonomy securiteInformatique = new Taxonomy("Sécurité Informatique", Arrays.asList(
                new Taxonomy("Pentesting & CTF", Arrays.asList(
                        new Taxonomy("Compétences", null, Arrays.asList("Pentest", "Capture the Flag (CTF)"))
                ), null),
                new Taxonomy("Cybersécurité", Arrays.asList(
                        new Taxonomy("Compétences", null, Arrays.asList("Sécurité des applications", "Vulgarisation de concepts techniques"))
                ), null)
        ), null);

        Taxonomy specialitesEtRoles = new Taxonomy("Spécialités et Rôles", Arrays.asList(
                new Taxonomy("Développement Backend", Arrays.asList(
                        new Taxonomy("Compétences", null, Arrays.asList("Java", "C#", "Python"))
                ), null),
                new Taxonomy("Développement Full Stack", Arrays.asList(
                        new Taxonomy("Compétences", null, Arrays.asList("Java/Angular", "ASP.NET/React"))
                ), null),
                new Taxonomy("Développement Frontend", Arrays.asList(
                        new Taxonomy("Compétences", null, Arrays.asList("React", "Angular"))
                ), null),
                new Taxonomy("DevOps", Arrays.asList(
                        new Taxonomy("Compétences", null, Arrays.asList("Infrastructure/ops", "Cloud Architecture"))
                ), null),
                new Taxonomy("Consulting & Coaching", Arrays.asList(
                        new Taxonomy("Compétences", null, Arrays.asList("Coach agile", "Consultant technique", "Formateur", "Speaker"))
                ), null)
        ), null);

        Taxonomy environnementsEtOutils = new Taxonomy("Environnements et Outils", Arrays.asList(
                new Taxonomy("Environnements de Développement", Arrays.asList(
                        new Taxonomy("Compétences", null, Arrays.asList("Windows", "Ubuntu", "Git", "GitHub", "GitLab CI", "GitHub Actions"))
                ), null),
                new Taxonomy("Outils de Collaboration et de Suivi", Arrays.asList(
                        new Taxonomy("Compétences", null, Arrays.asList("JIRA", "User Story", "SCRUM"))
                ), null)
        ), null);
        Taxonomy rootCategory = new Taxonomy("Root", Arrays.asList(
                langagesDeProgrammationEtTechnologies,
                architectureEtConception,
                methodologiesEtPratiques,
                competencesTransversalesEtSoftSkills,
                securiteInformatique,
                specialitesEtRoles,
                environnementsEtOutils
        ), null);
        //printCategory(rootCategory, 0);
        List<String> allItems = new ArrayList<>();
        runQuestionnaire(rootCategory,allItems);
        System.out.println(allItems);
    }
}
