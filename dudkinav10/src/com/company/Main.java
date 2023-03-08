package com.company;
import java.util.*;

class University {
    private final String name;
    private final int numStudents;
    private final int numTeachers;
    private final double avgScore;

    public University(String name, int numStudents, int numTeachers, double avgScore) {
        this.name = name;
        this.numStudents = numStudents;
        this.numTeachers = numTeachers;
        this.avgScore = avgScore;
    }

    public String getName() {
        return name;
    }

    public int getNumStudents() {
        return numStudents;
    }

    public int getNumTeachers() {
        return numTeachers;
    }

    public double getAvgScore() {
        return avgScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof University)) return false;
        University that = (University) o;
        return numStudents == that.numStudents &&
                numTeachers == that.numTeachers &&
                Double.compare(that.avgScore, avgScore) == 0 &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, numStudents, numTeachers, avgScore);
    }

    @Override
    public String toString() {
        return "Университет " + name +
                " (студентов: " + numStudents +
                ", преподавателей: " + numTeachers +
                ", средний балл ЕГЭ: " + avgScore + ")";
    }

    public static void main(String[] args) {
        University MIPT = new University("МФТИ", 1000, 200, 189);
        University MSU = new University("МГУ", 5000, 500, 178);
        University DVGUPS = new University("ДВГУПС", 3000, 400, 173);
        University SPbSU = new University("СПбГУ", 8000, 800, 179);
        University HSE = new University("ВШЭ", 4000, 300, 174);
        University MGIMO = new University("МГИМО", 2000, 150, 176);
        University MGU = new University("МГУ", 6000, 600, 175);
        University RUT = new University("РУТ", 1200, 100, 171);

        City moscow = new City("Москва", 13097539);
        City piter = new City("Санкт-Петербург", 5598486);
        City habarovsk = new City("Хабаровск", 617441);
        City dolgoprudniy = new City("Долгопрудный", 120907);

        Map<University, City> cityMap = new HashMap<>();
        cityMap.put(MIPT,moscow);
        cityMap.put(MSU,moscow);
        cityMap.put(DVGUPS,habarovsk);
        cityMap.put(SPbSU,piter);
        cityMap.put(HSE,moscow);
        cityMap.put(MGIMO,moscow);
        cityMap.put(MGU,moscow);
        cityMap.put(RUT,moscow);



        Set<University> technicalUniversities = new HashSet<>();
        technicalUniversities.add(RUT);
        technicalUniversities.add(MIPT);
        technicalUniversities.add(MSU);
        technicalUniversities.add(DVGUPS);
        technicalUniversities.add(SPbSU);

        Set<University> humanitarianUniversities = new HashSet<>();
        humanitarianUniversities.add(HSE);
        humanitarianUniversities.add(MGIMO);
        humanitarianUniversities.add(MGU);
        humanitarianUniversities.add(SPbSU);

        Set<University> allUniversities = new HashSet<>();
        allUniversities.addAll(technicalUniversities);
        allUniversities.addAll(humanitarianUniversities);

        Set<University> mixedUniversities = new HashSet<>(allUniversities);
        mixedUniversities.removeIf(university -> !(technicalUniversities.contains(university) && humanitarianUniversities.contains(university)));

        Set<University> specializedUniversities = new HashSet<>(allUniversities);
        specializedUniversities.removeAll(mixedUniversities);
        System.out.println("Все университеты: ");
        printUniversities(allUniversities);

        System.out.println("Университеты, выпускающие специалистов в обоих областях: ");
        printUniversities(mixedUniversities);

        System.out.println("Специализированные университеты: ");
        printUniversities(specializedUniversities);

        int totalStudents = calculateTotalStudents(allUniversities);
        double avgTeachers = calculateAvgTeachers(allUniversities);
        double avgScore = calculateAvgScore(allUniversities);

        System.out.println("Всего студентов в университетах: " + totalStudents);
        System.out.println("Среднее количество преподавателей в университетах: " + avgTeachers);
        System.out.println("Средний балл ЕГЭ по всем университетам: " + avgScore);

        System.out.println("Соотношение жителей города к одному студенту университета: ");
        for(University university : allUniversities){
            calculatePopulationCitybyStudent(cityMap, university);
        }
    }

    private static double calculatePopulationCitybyStudent(Map<University, City> cityMap, University university){
        double result = 0;
        City city = cityMap.get(university);
        double population = city.getPopulation();
        double numStudents = university.getNumStudents();
        result = population / numStudents;
        System.out.println("В институте " + university.getName() + " данное соотношение равняется " + Math.floor(result));
        return (result);
    }

    private static void printUniversities(Set<University> universities) {
        for (University university : universities) {
            System.out.println(university);
        }
    }

    private static int calculateTotalStudents(Set<University> universities) {
        int total = 0;
        for (University university : universities) {
            total += university.getNumStudents();
        }
        return total;
    }

    private static double calculateAvgTeachers(Set<University> universities) {
        double total = 0;
        for (University university : universities) {
            total += university.getNumTeachers();
        }
        return total / universities.size();
    }

    private static double calculateAvgScore(Set<University> universities) {
        double total = 0;
        for (University university : universities) {
            total += university.getAvgScore();
        }
        return total / universities.size();
    }
}


