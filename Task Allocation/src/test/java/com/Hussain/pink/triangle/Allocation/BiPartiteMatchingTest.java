package com.Hussain.pink.triangle.Allocation;

import com.Hussain.pink.triangle.Model.Graph.Graph;
import com.Hussain.pink.triangle.Model.Graph.Node;
import com.Hussain.pink.triangle.Model.Graph.NodeType;
import com.Hussain.pink.triangle.Organisation.Employee;
import com.Hussain.pink.triangle.Organisation.Skill;
import com.Hussain.pink.triangle.Organisation.Task;
import org.junit.Test;

import java.util.LinkedHashSet;

import static org.junit.Assert.assertEquals;

/**
 * Created by Hussain on 26/01/2015.
 */
public class BiPartiteMatchingTest {

    @Test
    public void testAllocateTasks(){
        Graph<Node<Employee>, Node<Task>> expectedGraph = new Graph<>();

        final Skill java = new Skill("Java",1);
        final Skill uml = new Skill("UML",1);

        LinkedHashSet<Skill> e1SkillSet = new LinkedHashSet<Skill>(){{
            add(java); add(uml);
        }};

        LinkedHashSet<Skill> e2SkillSet = new LinkedHashSet<Skill>(){{
            add(java);
        }};

        Node<Employee> e1 = new Node<>(new Employee(1,"e1",e1SkillSet,0), NodeType.EMPLOYEE);
        Node<Employee> e2 = new Node<>(new Employee(2,"e2",e2SkillSet,0),NodeType.EMPLOYEE);

        Node<Task> t1 = new Node<>(new Task(3,"T1",100,1L,1L,false,e1SkillSet),NodeType.TASK);
        Node<Task> t2 = new Node<>(new Task(4,"T2",102,1L,1L,false,e2SkillSet),NodeType.TASK);

        expectedGraph.addEdge(e1,t1);
        expectedGraph.addEdge(e2,t2);

        Graph<Node<Employee>,Node<Task>> testGraph = new Graph<>();

        testGraph.addEmployeeNode(e1);
        testGraph.addEmployeeNode(e2);

        testGraph.addTaskNode(t1);
        testGraph.addTaskNode(t2);

        TaskAllocationMethod testTaskAllocationMethod = new BiPartiteMatching();
        testTaskAllocationMethod.allocateTasks(testGraph);

        assertEquals(expectedGraph,testGraph);
    }

    @Test
    public void testBiPartiteMatching(){
        Graph<Node<Employee>, Node<Task>> expectedGraph = buildExpectedGraph();

        Node<Employee> e1 = new Node<>(new Employee(1,"E1",null,0),NodeType.EMPLOYEE);
        Node<Employee> e2 = new Node<>(new Employee(2,"E2",null,0),NodeType.EMPLOYEE);
        Node<Employee> e3 = new Node<>(new Employee(3,"E3",null,0),NodeType.EMPLOYEE);
        Node<Employee> e4 = new Node<>(new Employee(4,"E4",null,0),NodeType.EMPLOYEE);
        Node<Employee> e5 = new Node<>(new Employee(5,"E5",null,0),NodeType.EMPLOYEE);

        Node<Task> t6 = new Node<>(new Task(6,"T6",100,1L,1L,false,null),NodeType.TASK);
        Node<Task> t7 = new Node<>(new Task(7,"T7",100,1L,1L,false,null),NodeType.TASK);
        Node<Task> t8 = new Node<>(new Task(8,"T8",100,1L,1L,false,null),NodeType.TASK);
        Node<Task> t9 = new Node<>(new Task(9,"T9",100,1L,1L,false,null),NodeType.TASK);

        Graph<Node<Employee>, Node<Task>> testGraph = new Graph<>();

        testGraph.addEmployeeNode(e1);
        testGraph.addEmployeeNode(e2);
        testGraph.addEmployeeNode(e3);
        testGraph.addEmployeeNode(e4);
        testGraph.addEmployeeNode(e5);

        testGraph.addTaskNode(t6);
        testGraph.addTaskNode(t7);
        testGraph.addTaskNode(t9);
        testGraph.addTaskNode(t8);

        testGraph.addEdge(e1,t7);

        testGraph.addEdge(e2,t6);

        testGraph.addEdge(e3,t7);
        testGraph.addEdge(e3,t9);

        testGraph.addEdge(e4,t8);

        testGraph.addEdge(e5,t8);
        testGraph.addEdge(e5,t9);

        BiPartiteMatching matching = new BiPartiteMatching();
        matching.biPartiteMatching(testGraph);

        assertEquals(expectedGraph,testGraph);

    }

    private Graph<Node<Employee>, Node<Task>> buildExpectedGraph(){
        Graph<Node<Employee>, Node<Task>> graph = new Graph<>();

        Node<Employee> e1 = new Node<>(new Employee(1,"E1",null,0),NodeType.EMPLOYEE);
        Node<Employee> e2 = new Node<>(new Employee(2,"E2",null,0),NodeType.EMPLOYEE);
        Node<Employee> e3 = new Node<>(new Employee(3,"E3",null,0),NodeType.EMPLOYEE);
        Node<Employee> e4 = new Node<>(new Employee(4,"E4",null,0),NodeType.EMPLOYEE);

        Node<Task> t6 = new Node<>(new Task(6,"T6",100,1L,1L,false,null),NodeType.TASK);
        Node<Task> t7 = new Node<>(new Task(7,"T7",100,1L,1L,false,null),NodeType.TASK);
        Node<Task> t8 = new Node<>(new Task(8,"T8",100,1L,1L,false,null),NodeType.TASK);
        Node<Task> t9 = new Node<>(new Task(9,"T9",100,1L,1L,false,null),NodeType.TASK);

        graph.addEdge(e1,t7);
        graph.addEdge(e2,t6);
        graph.addEdge(e3,t9);
        graph.addEdge(e4,t8);

        return graph;
    }
}
