# Simple-Animations
This is a project, where there's a class "AnimationsHelper" it's a static class that has the following functions to easily animate views using property animations
:
1- MoveVertically:  Moves  a view vertically by providing base and to points
2- animateHeight: animates height property for a view
3- animateHeightToFillParent: animate a child view to fill it's parent
4- moveUpToParent: moves an element vertically to the top of parent by providing the child and parent views
5-enterHorizontally: Moves a child element from side of the screen to center in parent
6- enterHorizontallyWithSlide : same as enterHorizontally but with a slide back animation (extra animation)

the good thing about these methods, is that you can simply animate a view inside it's parent by only providing that view, the parent view and the duration of animation
no need to implement translations or property animations, all is already made and you can modify the code to suit you more :)

Here's the sample activity

![alt tag](http://i.giphy.com/3o7TKBA1YnryIt9kru.gif)



By order
the progress bar uses MooveUpToParent,
then on end Listener, the lighter arc is animated by animateHeightToFillParent, then calls animateHeight(to give the kinda bounce effect)
after that, the coolest part of the app comes :D the "panda hi" 
it uses enterHorizontallyWithSlide and that's it

you are very welcome to suggest enhancements and better practices and to add stuff 
Thanks :) and hope it helps  in one way or another :D
peace.
