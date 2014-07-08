#version 120

uniform sampler2D DiffuseSampler;
uniform vec2 InSize;

varying vec2 texCoord;
varying float ratio;

void main()
{
    float height = InSize.y/2;
    vec4 color = texture2D(DiffuseSampler, texCoord);
    float average = (color.r+color.g+color.b)/3; // Convert to green scale
    vec4 greenScale = mix(vec4(0,average,0,1), vec4(0,1,0,1), 0.125); // Add some green!
    float ratio = 0;
    if(int(mod(texCoord.y*height, 4)) == 0) // We check how 'far' is the black band
        ratio = 0.5;
    else if(int(mod(texCoord.y*height, 4)) == 1) // Same
        ratio = 0.25;
    else if(int(mod(texCoord.y*height, 4)) == 3) // Same
        ratio = 0.25;
    greenScale = mix(greenScale, vec4(0,0,0,1), ratio); // We mix the colors
    gl_FragColor = greenScale; // And export the result
}
