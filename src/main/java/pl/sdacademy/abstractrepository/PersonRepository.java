package pl.sdacademy.abstractrepository;

public class PersonRepository extends AbstractRepository<Person> {
    public PersonRepository(String filename) {
        super(filename);
    }

    @Override
    protected Person createEntity(String fileLine) {
        String[] fileLineParts = fileLine.split(",");
        int id = Integer.parseInt(fileLineParts[0]);
        String firstName = fileLineParts[1];
        String lastName = fileLineParts[2];
        int age = Integer.parseInt(fileLineParts[3]);
        return new Person(id, firstName, lastName, age);
    }

    @Override
    protected String createFileLine(Person entity) {
        return entity.getId() + "," + entity.getFirstName()
                + "," + entity.getLastName() + "," + entity.getAge();
    }
}
