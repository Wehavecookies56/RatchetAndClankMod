#version 120

uniform sampler2D DiffuseSampler;

varying vec2 texCoord;
varying float ratio;

void main(){
    vec4 color = texture2D(DiffuseSampler, texCoord);
    if(mod(texCoord.y, ratio*4) <= ratio*2)
    {
    	color.rgb = mix(color.rgb, vec3(0,0,0), 0.35);
    }
    else if(mod(texCoord.y, ratio*4) <= ratio*3)
    {
    	color.rgb = mix(color.rgb, vec3(0,30.0/256.0,0), 0.35);
    }
    else
    {
    	color.rgb = mix(color.rgb, vec3(0,137.0/256.0,0), 0.35);
    }
    
    gl_FragColor = color;
}
