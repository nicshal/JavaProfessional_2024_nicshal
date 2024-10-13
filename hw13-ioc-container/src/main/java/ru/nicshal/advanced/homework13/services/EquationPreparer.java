package ru.nicshal.advanced.homework13.services;

import ru.nicshal.advanced.homework13.model.Equation;

import java.util.List;

public interface EquationPreparer {
    List<Equation> prepareEquationsFor(int base);
}
