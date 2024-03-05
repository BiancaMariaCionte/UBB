
from options.iteration_1.add import add_to_the_end, insert
from options.iteration_3.filter import filter_mul, filter_greater
from options.iteration_2.get import less, sorted_list, sorted_higher
from options.iteration_1.modify import remove_index, remove_from_to_index, replace
from options.iteration_2.obtain import avg, minimum, mul


# Feature 1
def test_add():
    assert add_to_the_end(score_list=[1, 2, 3, 4], value=5) == [1, 2, 3, 4, 5]
    assert add_to_the_end(score_list=[], value=1) == [1]
    assert add_to_the_end(score_list=[1], value=2) == [1, 2]


def test_insert():
    assert insert(score_list=[1, 3, 4], index=1, value=2) == [1, 2, 3, 4]
    assert insert(score_list=[], index=0, value=1) == [1]
    assert insert(score_list=[1, 3, 4], index=1, value=2) == [1, 2, 3, 4]


# Feature 2
def test_remove_index():
    assert remove_index(score_list=[1, 2, 3, 4], index=0) == [2, 3, 4]
    assert remove_index(score_list=[], index=1) == []
    assert remove_index(score_list=[1], index=0) == []


def test_remove_from_to_index():
    assert remove_from_to_index(score_list=[1, 2, 3, 4, 5], from_index=0, to_index=2) == [4, 5]
    assert remove_from_to_index(score_list=[], from_index=2, to_index=6) == []
    assert remove_from_to_index(score_list=[1, 2, 3, 4], from_index=0, to_index=3) == []


def test_replace():
    assert replace(score_list=[1, 2, 3, 4], index=2, new_value=5) == [1, 2, 5, 4]
    assert replace(score_list=[1], index=0, new_value=2) == [2]
    assert replace(score_list=[], index=1, new_value=1) == []


# Feature 3
def test_less():
    assert less(score_list=[1, 2, 3, 4, 5], value=4) == ['participant 0', 'participant 1', 'participant 2']
    assert less(score_list=[], value=5) == []
    assert less(score_list=[5], value=10) == ['participant 0']
    assert less(score_list=[5], value=3) == []


def test_sorted_list():
    assert sorted_list(score_list=[9, 4, 2, 1, 0, 5]) == [['participant 4', 0], ['participant 3', 1], ['participant 2', 2], ['participant 1', 4], ['participant 5', 5], ['participant 0', 9]]
    assert sorted_list(score_list=[1]) == [['participant 0', 1]]
    assert sorted_list(score_list=[]) == []


def test_sorted_higher():
    assert sorted_higher(score_list=[4, 3, 7, 2, 4, 9], value=6) == [['participant 2', 7], ['participant 5', 9]]
    assert sorted_higher(score_list=[5, 1], value=6) == []
    assert sorted_higher(score_list=[5, 3, 8, 1, 6], value=3) == [['participant 0', 5], ['participant 4', 6], ['participant 2', 8]]


# Feature 4
def test_avg():
    assert avg(score_list=[1, 2, 3, 4, 5], from_index=0, to_index=4) == 3
    assert avg(score_list=[3], from_index=0, to_index=0) == 3
    assert avg(score_list=[0, 0, 0, 0], from_index=1, to_index=3) == 0


def test_minimum():
    assert minimum(score_list=[1, 5, 3, 8, 4, 2], from_index=1, to_index=4) == 3
    assert minimum(score_list=[2, 3], from_index=0, to_index=1) == 2
    assert minimum(score_list=[4], from_index=0, to_index=0) == 4


def test_mul():
    assert mul(score_list=[1, 3, 4, 6, 2, 3, 6, 7, 8, 5], value=2, from_index=2, to_index=7) == [4, 6, 2, 6]
    assert mul(score_list=[1, 7, 8, 5, 4, 2], value=3, from_index=0, to_index=5) == []
    assert mul(score_list=[4], value=4, from_index=0, to_index=0) == [4]


# Feature 5
def test_filter_mul():
    assert filter_mul(score_list=[1, 2, 3, 4, 5, 6, 7, 8], value=2) == [2, 4, 6, 8]
    assert filter_mul(score_list=[4, 5, 7], value=3) == []
    assert filter_mul(score_list=[4], value=3) == []
    assert filter_mul(score_list=[2], value=2) == [2]


def test_filter_greater():
    assert filter_greater(score_list=[1, 2, 3, 4, 5, 6, 7], value=4) == [5, 6, 7]
    assert filter_greater(score_list=[5], value=6) == []
    assert filter_greater(score_list=[], value=1) == []
    assert filter_greater(score_list=[5], value=4) == [5]


def test_methods():
    # Feature 1
    test_add()
    test_insert()
    # Feature 2
    test_remove_index()
    test_remove_from_to_index()
    test_replace()
    # Feature 3
    test_less()
    test_sorted_list()
    test_sorted_higher()
    # Feature 4
    test_avg()
    test_minimum()
    test_mul()
    # Feature 5
    test_filter_mul()
    test_filter_greater()
