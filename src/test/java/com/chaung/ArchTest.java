package com.chaung;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.chaung");

        noClasses()
            .that()
                .resideInAnyPackage("com.chaung.service..")
            .or()
                .resideInAnyPackage("com.chaung.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.chaung.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
