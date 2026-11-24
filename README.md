# Лаборатори №4: Нэгжийн тестийн эхлэл — JUnit 5

- **Оюутны нэр:** Т.Төгөлдөр
- **Оюутны код:** [B242270050]
- **Тестийн методын тоо:** `8` (параметржүүлсэн `@CsvSource` тестүүдийн мөрүүдийг оролцуулан нийт `17` тест тохиолдол)

### `java -version` бүтэн гаралт

java version "25.0.2" 2026-01-20 LTS
Java(TM) SE Runtime Environment (build 25.0.2+10-LTS-69)
Java HotSpot(TM) 64-Bit Server VM (build 25.0.2+10-LTS-69, mixed mode, sharing)

### `mvn -version` бүтэн гаралт

Apache Maven 3.10.0 (c43a36b8d67be7e0805a411bc0898af1a51f5472)
Maven home: /opt/homebrew/Cellar/maven/3.10.0/libexec
Java version: 27.0.1.0.3, vendor: Homebrew, runtime: /opt/homebrew/Cellar/openjdk/27.0.1.0.3/libexec/openjdk.jdk/Contents/Home
Default locale: en_MN, platform encoding: UTF-8, time zone: Asia/Ulaanbaatar
OS name: "mac os x", version: "26.0", arch: "aarch64", family: "mac"

---

## 1. Гүйцэтгэсэн Даалгаврын Хураангуй ба Тестийн Тоо

- **GradeCalculator хэрэгжүүлэлт:** Оюутны үсгэн дүн тооцох (`letterGrade`) болон бүрэлдэхүүн хэсгүүдийн нийлбэр оноо тооцох (`totalScore`) логикийг хязгаарын шалгалт болон `IllegalArgumentException` боловсруулалттайгаар хэрэгжүүлсэн.
- **Нэгжийн тестүүд:** Arrange-Act-Assert (AAA) бүтэцтэй, `@DisplayName` бүхий тестүүдийн методууд болон `@ParameterizedTest` (`@CsvSource`) тестүүдийг `GradeCalculatorTest` дээр бичсэн.
- **Тестийн тооны тааралж:** `results/mvn-test.txt` файлын төгсгөлд `Tests run: 17, Failures: 0, Errors: 0, Skipped: 0` гарсан бөгөөд энэ нь параметржүүлсэн тестүүдийн `@CsvSource` мөр бүрийг оролцуулсан нийт 17 тест тохиолдлын дүн болно.

---

## 2. Мутацийн Тестийн Үр Дүн (Mutation Testing)

- **Мутацийн согог:** `GradeCalculator.java` доторх `if (score >= 90)` нөхцөлийг зориуд `if (score > 90)` болгон өөрчилж `mvn test 2>&1 | tee results/mvn-test-mutant.txt` ажиллуулсан.
- **Илрүүлсэн тест:** Уг мутантыг `GradeCalculatorTest.ninetyIsExactlyA` болон `GradeCalculatorTest.letterGradeBoundaries` тестүүд барьж авч, `expected: <A> but was: <B>` зөрүүгээр `Tests run: 17, Failures: 2` буюу `BUILD FAILURE` гарсан.
- **Засвар:** Мутацийг буцаан `if (score >= 90)` болгож зассанаар `mvn test` дахин 17/17 амжилттай давж `BUILD SUCCESS` гарсан.

---

## 3. Дүгнэлт ба Ажиглалт

1. Apache Maven төслийн бүтцэд JUnit 5 фрэймворк ашиглан Java классын нэгжийн тестийг Arrange-Act-Assert (AAA) бүтцээр бичиж сурлаа.
2. `letterGrade` болон `totalScore` методуудын хязгаарын утгууд (`90.0`, `89.99`, `59.99`, `0`, `100` болон бүрэлдэхүүн оноонууд) дээр тестүүд бичиж, хүчингүй оролтуудад `IllegalArgumentException` шидэж буйг баталгаажуулсан.
3. `@ParameterizedTest` болон `@CsvSource` ашиглан олон оролтын багцыг код давхардуулахгүйгээр шалгах аргад суралцлаа.
4. Мутацийн туршилтаар (`score >= 90` -> `score > 90`) хязгаарын нөхцөл суларсан үед ямар тестүүд унаж согогийг илрүүлж байгааг бодитоор харсан.
5. `ninetyIsExactlyA` зэрэг зааг утгын тестүүд мутантыг унагаснаар нэгжийн тестийн чанар болон хязгаарын шинжилгээний ач холбогдлыг бататгалаа.
6. `results/mvn-test.txt` болон `results/mvn-test-mutant.txt` лог файлуудыг хадгалж, Git commit түүхээр үе шат бүрийн нотолгоог цэгцтэй бүрдүүллээ.

---

## 4. Нотолгооны Файлууд

- `results/mvn-test.txt` — Бүх 17 тест амжилттай давсан (PASS) лог.
- `results/mvn-test-mutant.txt` — Мутант үүсгэсэн үеийн уналтын (FAIL / `BUILD FAILURE`) лог.
