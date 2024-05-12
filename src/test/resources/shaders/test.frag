#version 120

uniform vec3 testColor;

void main() {
    gl_FragColor = vec4(testColor, 1.0);
}
