package com.app.todo.model;

import com.app.todo.AssertAnnotations;
import com.app.todo.ReflectTool;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.*;



public class TaskTest {


    @Test
    public void typeAnnotationsTest() {

        AssertAnnotations.assertType(
                Task.class, Entity.class, Table.class);

    }

    @Test
    public void fieldAnnotationsTest() {

        AssertAnnotations.assertField(Task.class, "id",Id.class,GeneratedValue.class);
        AssertAnnotations.assertField(Task.class, "name",Column.class);
        AssertAnnotations.assertField(Task.class, "done",Column.class);
        AssertAnnotations.assertField(Task.class, "project",ManyToOne.class,JoinColumn.class);
    }

    @Test
    public void gettersTest() {
        AssertAnnotations.assertGetters(Task.class,"id","name","done","project");
    }

    @Test
    public void settersTest() {
        AssertAnnotations.assertSetters(Task.class,"id","name","done","project");
    }

    @Test
    public void entityTest() {

        Entity entity
                = ReflectTool.getClassAnnotation(Task.class, Entity.class);

        Assert.assertEquals("", entity.name());
    }

    @Test
    public void tableTest() {

        Table table
                = ReflectTool.getClassAnnotation(Task.class, Table.class);

        Assert.assertEquals("", table.name());
    }

    @Test
    public void idTest() {

        GeneratedValue a
                = ReflectTool.getFieldAnnotation(
                Task.class, "id", GeneratedValue.class);

        Assert.assertEquals("", a.generator());
        Assert.assertEquals(GenerationType.AUTO, a.strategy());
    }

    @Test
    public void nameTest() {

        Column c
                = ReflectTool.getFieldAnnotation(
                Task.class, "name", Column.class);

        Assert.assertEquals("", c.name());
    }

    @Test
    public void doneTest() {

        Column c
                = ReflectTool.getFieldAnnotation(
                Task.class, "done", Column.class);

        Assert.assertEquals("", c.name());
    }

    @Test
    public void projectTest() {


        ManyToOne manyToOne = ReflectTool.getFieldAnnotation(Task.class,"project",ManyToOne.class);
        JoinColumn joinColumn = ReflectTool.getFieldAnnotation(Task.class,"project",JoinColumn.class);

        Assert.assertEquals(FetchType.LAZY,manyToOne.fetch());
        Assert.assertEquals("project_id",joinColumn.name());

    }


}