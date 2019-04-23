package com.app.todo.model;

import com.app.todo.AssertAnnotations;
import com.app.todo.ReflectTool;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import javax.persistence.*;
import java.util.Arrays;

public class ProjectTest {

    @Test
    public void typeAnnotationsTest() {

        AssertAnnotations.assertType(
                Project.class, Entity.class, Table.class);

    }

    @Test
    public void fieldAnnotationsTest() {

        AssertAnnotations.assertField(Project.class, "id",Id.class,GeneratedValue.class);
        AssertAnnotations.assertField(Project.class, "name",Column.class);
        AssertAnnotations.assertField(Project.class, "tasks",OneToMany.class);
    }

    @Test
    public void gettersTest() {
        AssertAnnotations.assertGetters(Project.class,"id","name","tasks");
    }

    @Test
    public void settersTest() {
        AssertAnnotations.assertSetters(Project.class,"id","name","tasks");
    }

    @Test
    public void entityTest() {

        Entity entity
                = ReflectTool.getClassAnnotation(Project.class, Entity.class);

        Assert.assertEquals("", entity.name());
    }

    @Test
    public void tableTest() {

        Table table
                = ReflectTool.getClassAnnotation(Project.class, Table.class);

        Assert.assertEquals("", table.name());
    }

    @Test
    public void idTest() {

        GeneratedValue a
                = ReflectTool.getFieldAnnotation(
                Project.class, "id", GeneratedValue.class);

        Assert.assertEquals("", a.generator());
        Assert.assertEquals(GenerationType.AUTO, a.strategy());
    }

    @Test
    public void nameTest() {

        Column c
                = ReflectTool.getFieldAnnotation(
                Project.class, "name", Column.class);

        Assert.assertEquals("", c.name());
    }

    @Test
    public void tasksTest() {


        OneToMany oneToMany = ReflectTool.getFieldAnnotation(Project.class,"tasks",OneToMany.class);

        Assert.assertEquals("project", oneToMany.mappedBy());

        Assert.assertEquals(Arrays.asList(CascadeType.ALL).toArray(), oneToMany.cascade());
        Assert.assertEquals(FetchType.LAZY, oneToMany.fetch());
        Assert.assertEquals(true, oneToMany.orphanRemoval());
    }


}