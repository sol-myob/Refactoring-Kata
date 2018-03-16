package algorithm;
import java.util.ArrayList;
import java.util.List;

public class PeopleFinder {
	private final List<Person> People;

	public PeopleFinder(List<Person> p) {
		People = p;
	}

	public PeopleFound Find(FinderType finderType) {

		List<PeopleFound> tr = new ArrayList<PeopleFound>();

		for (int i = 0; i < People.size() - 1; i++) {
			for (int j = i + 1; j < People.size(); j++) {
				PeopleFound r = new PeopleFound();
				if (People.get(i).birthDate.getTime() < People.get(j).birthDate.getTime()) {
					r.Person1 = People.get(i);
					r.Person2 = People.get(j);
				} else {
					r.Person1 = People.get(j);
					r.Person2 = People.get(i);
				}
				r.AgeDifference = r.Person2.birthDate.getTime() - r.Person1.birthDate.getTime();
				tr.add(r);
			}
		}

		if (tr.size() < 1) {
			return new PeopleFound();
		}

		PeopleFound answer = tr.get(0);
		for (PeopleFound result : tr) {
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
