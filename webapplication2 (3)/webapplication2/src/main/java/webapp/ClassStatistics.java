package webapp;

import java.util.*;

public class ClassStatistics{
    private double average;
    private double median;
    private int max;
    private int min;

    public static ClassStatistics analyzeTheGrades(List<ClassGrade> classGrades){
        return new ClassStatistics(classGrades);
    }
    private ClassStatistics(List<ClassGrade> classGrades){
        analyze(classGrades);
    }
        private void analyze(List<ClassGrade> classGrades){
        List<Integer> grades=new ArrayList<>();
        extractGrades(grades,classGrades);
        Collections.sort(grades);
        calculateAverage(grades);
        calculateMedian(grades);
        findMax(grades);
        findMin(grades);
    }
    private void extractGrades(List<Integer> grades,List<ClassGrade> classGrades)
    {
        for(int i=0;i<classGrades.size();i++){
            grades.add(classGrades.get(i).getGrade());
        }
    }
    private void calculateAverage(List<Integer> grades){
        average= grades.stream().mapToDouble(d->d).average().orElse(0.0);
    }
    private void calculateMedian(List<Integer> grades){
        int numberOfGrades=grades.size();
        if(numberOfGrades%2==0){
            median=(grades.get(numberOfGrades/2)+grades.get(numberOfGrades/2-1))/2.0;
        }
        else{
            median=grades.get(numberOfGrades/2);
        }
    }
    private void findMax(List<Integer> grades){
        int numberOfGrades=grades.size();
        max= grades.get(numberOfGrades-1);
    }
    private void findMin(List<Integer> grades){
        min= grades.get(0);
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double getMedian() {
        return median;
    }

    public void setMedian(double median) {
        this.median = median;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    @Override
    public String toString() {
        return "CourseStatistics{" +
                "average=" + average +
                ", median=" + median +
                ", max=" + max +
                ", min=" + min +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassStatistics that = (ClassStatistics) o;
        return Double.compare(that.average, average) == 0 && Double.compare(that.median, median) == 0 && max == that.max && min == that.min;
    }

    @Override
    public int hashCode() {
        return Objects.hash(average, median, max, min);
    }
}
