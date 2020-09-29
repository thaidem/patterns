//Приспособленец (англ. flyweight, «легковесный (элемент)») — структурный шаблон проектирования,
// при котором объект, представляющий себя как уникальный экземпляр в разных местах программы,
// по факту не является таковым.

import java.util.WeakHashMap;

class MainFlyWeight {
  public static void main(String[] args) {

    StudentCache cache = new StudentCache();
    StudentUniversityInfo mike = cache.getStudentUniversityInfo("management");
    StudentUniversityInfo mike2 = cache.getStudentUniversityInfo("management");
    System.out.println(mike == mike2);
  }
}

class Hostel {}

class StudentPersonalInfo {
  String name;
  int age;
  String homeAddress;
  int course;
  double averageMark;
}

class StudentUniversityInfo {
  String faculty;
  String universityCity;
  Hostel hostelAddress;

  public StudentUniversityInfo(String faculty, String universityCity, Hostel hostelAddress) {
    this.faculty = faculty;
    this.universityCity = universityCity;
    this.hostelAddress = hostelAddress;
  }
}

class StudentCache {
  private static final WeakHashMap<String, StudentUniversityInfo> studentsUniversityInfo = new WeakHashMap<>();

  public StudentUniversityInfo getStudentUniversityInfo(String faculty) {
    StudentUniversityInfo studentUniversityInfo = studentsUniversityInfo.get(faculty);
    if (studentUniversityInfo == null) {
      studentUniversityInfo = createStudentInfo(faculty);
      studentsUniversityInfo.put(faculty, studentUniversityInfo);
    }
    return studentUniversityInfo;
  }

  private StudentUniversityInfo createStudentInfo(String faculty) {
    switch (faculty) {
      case "informatic":
        return new StudentUniversityInfo(faculty, "New York", new Hostel());
      case "management":
        return new StudentUniversityInfo(faculty, "Boston", new Hostel());
      default:
        throw new IllegalArgumentException("no faculty");
    }

  }
}


