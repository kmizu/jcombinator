package com.github.kmizu.jcombinator.example.regex;

public class RegexInterpreter {
    private static class InterpreterContext {
        String input;
        InterpreterContext(String input) {
            this.input = input;
        }
        public void advance(int length) {
            this.input = input.substring(length);
        }
    }
    private static class ExpressionVisitor implements  AstNode.RVisitor<Lazy<String>, InterpreterContext> {
        @Override
        public Lazy<String> visitRGrouped(AstNode.RGrouped node, InterpreterContext context) {
            return null;
        }

        @Override
        public Lazy<String> visitRString(AstNode.RString node, InterpreterContext context) {
            return null;
        }

        @Override
        public Lazy<String> visitRChoice(AstNode.RChoice node, InterpreterContext context) {
            return null;
        }

        @Override
        public Lazy<String> visitRSequence(AstNode.RSequence node, InterpreterContext context) {
            return null;
        }

        @Override
        public Lazy<String> visitRRepeat0(AstNode.RRepeat0 node, InterpreterContext context) {
            return null;
        }

        @Override
        public Lazy<String> visitRRepeat1(AstNode.RRepeat1 node, InterpreterContext context) {
            return null;
        }
    }
}
