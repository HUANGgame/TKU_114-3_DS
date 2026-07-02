public class HealthCalculator {
    public static void main(String[] args) {
        double heightMeters = 1.70;
        double weightKg = 65.0;
        double bmi = weightKg / (heightMeters * heightMeters);

        System.out.printf("BMI: %.2f%n", bmi);
    }
}

