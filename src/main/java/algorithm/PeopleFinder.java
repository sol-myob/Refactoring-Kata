package algorithm;
import java.util.ArrayList;
import java.util.List;

public class PeopleFinder {
	private final List<Person> People;

	public PeopleFinder(List<Person> p) {
		People = p;
	}

	public PersonMatch Find(FinderType finderType) {

		List<PersonMatch> peopleMatched = new ArrayList<PersonMatch>();

		for (int i = 0; i < People.size() - 1; i++) {
			for (int j = i + 1; j < People.size(); j++) {
				PersonMatch r = new PersonMatch();
				if (People.get(i).birthDate.getTime() < People.get(j).birthDate.getTime()) {
					r.Person1 = People.get(i);
					r.Person2 = People.get(j);
				} else {
					r.Person1 = People.get(j);
					r.Person2 = People.get(i);
				}
				r.AgeDifference = r.Person2.birthDate.getTime() - r.Person1.birthDate.getTime();
				peopleMatched.add(r);
			}
		}

		if (peopleMatched.size() < 1) {
			return new PersonMatch();
		}

		PersonMatch answer = peopleMatched.get(0);
		for (PersonMatch result : peopleMatched) {
			switch (finderType) {
				case CLOSEST_AGE_DIFFERENCE:
					if (result.AgeDifference < answer.AgeDifference) {
						answer = result;
					}
					break;

				case FURTHEST_AGE_DIFFERENCE:
					if (result.AgeDifference > answer.AgeDifference) {
						answer = result;
					}
					break;
			}
		}

		return answer;
	}
}
